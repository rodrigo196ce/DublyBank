package br.com.beta.dublybank.model;

import javax.persistence.Embeddable;

@Embeddable
public class Authority {
    private String authority;

    public Authority(){}
    public Authority(String authority){
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
