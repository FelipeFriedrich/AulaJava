/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author 631910142
 */
public class ClientePJ extends Cliente {
    private String CNPJ;

    public String getCPF() {
        return CNPJ;
    }

    public void setCPF(String CNPJ) {
        this.CNPJ = CNPJ;
    }
    
}
