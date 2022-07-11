package br.com.beta.dublybank.service;

import br.com.beta.dublybank.model.Deposito;
import br.com.beta.dublybank.model.TransferenciaPix;
import br.com.beta.dublybank.model.User;
import br.com.beta.dublybank.repository.DepositoRepository;
import br.com.beta.dublybank.repository.TransferenciaPixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private DepositoRepository depositoRepository;

    @Autowired
    private TransferenciaPixRepository transferenciaPixRepository;

    public List<Deposito> findDepositoByUsername(){
        return this.depositoRepository
                .findDepositoByUsername(SecurityContextHolder.getContext().getAuthentication().getName()
                        ,Sort.by("data").descending());
    }

    public List<TransferenciaPix> findTransferenciaUsername(){
        return this.transferenciaPixRepository
                .findTransferenciaByUsername(SecurityContextHolder.getContext().getAuthentication().getName()
                        ,Sort.by("data").descending());
    }

}
