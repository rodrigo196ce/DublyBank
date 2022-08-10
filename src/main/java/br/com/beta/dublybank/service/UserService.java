package br.com.beta.dublybank.service;

import br.com.beta.dublybank.model.User;
import br.com.beta.dublybank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContaService contaService;

    @Transactional
    public void save(User user){
       User u = this.userRepository.save(user);
       u.setConta(this.contaService.save(u));
       u.setPassword(new BCryptPasswordEncoder().encode(u.getPassword()));
    }

    public User findUser(){
        // Retorna apenas o User sem nenhum relacionamento.
        return this.userRepository
                .findUser(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public User findUserAndConta(){
        // Retornar o User e o relacionamento com Conta.
        return this.userRepository
                .findUserAndConta(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public Boolean validarIdentidade(String senhaValidacao){
        BCryptPasswordEncoder crip = new BCryptPasswordEncoder();
        User user = this.findUserAndConta();
        if(crip.matches(senhaValidacao,user.getPassword())==false){
            return false;
        }else{
            return true;
        }
    }

    public User findUserContainChavePix(String chave){
        User userDestinatario = this.userRepository.findUserContainChavePix(chave);
        if(userDestinatario!=null){
            if(userDestinatario.getCpf().equals(chave)){
                if(userDestinatario.getConta().getChavePixCpfRegistrada()==null){
                    return null;
                }else{
                    return userDestinatario;
                }
            }else if(userDestinatario.getEmail().equals(chave)){
                if(userDestinatario.getConta().getChavePixEmailRegistrada()==null){
                    return null;
                }else{
                    return userDestinatario;
                }
            }else if(userDestinatario.getConta().getChavePixAleatoria()!=null){
                if(userDestinatario.getConta().getChavePixAleatoria().equals(chave)){
                    return userDestinatario;
                }else{
                    return null;
                }
            }
        }
        return null;
    }

    public User findValueTelefone(String telefone){
       return this.userRepository.findValueTelefone(telefone);
    }


}
