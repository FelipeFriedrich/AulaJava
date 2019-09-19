/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Cidade;
import Model.Cliente;
import Model.ClientePF;
import Model.ClientePJ;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



/**
 *
 * @author 631910142
 */
public class ClienteDAO {
    public static void inserir( Cliente cliente){
    String cpf_cnpj = ""; 
    if (cliente.getTipo().equals(Cliente.PESSOA_FISICA)){
        ClientePF pf = (ClientePF) cliente;
        cpf_cnpj = pf.getCPF();
    }else{
        ClientePJ pj = (ClientePJ) cliente;
        cpf_cnpj = pj.getCNPJ();
    }
    String query = "INSERT INTO clientes(nome,email, cpf_cnpj," 
            + "receberEmail,tipo, codCidade) VALUES ("
            + " '" + cliente.getNome() + "',"
            + " '" + cliente.getEmail() + "',"
            + " '" + cpf_cnpj + "',"  
            + cliente.isReceberEmail() + ", "
            + " '" + cliente.getTipo() + "',"
            + cliente.getCidade().getId() + ") ";
    conexao.executar(query);
    }
    
    public static void editar( Cliente cliente){
    String cpf_cnpj = ""; 
    if (cliente.getTipo().equals(Cliente.PESSOA_FISICA)){
        ClientePF pf = (ClientePF) cliente;
        cpf_cnpj = pf.getCPF();
    }else{
        ClientePJ pj = (ClientePJ) cliente;
        cpf_cnpj = pj.getCNPJ();
    }
    String query = "update clientes clientes set "
            + " nome = '" + cliente.getNome() + "',"
            + " email = '" + cliente.getEmail() + "',"
            + " cpf_cnpj = '" + cpf_cnpj + "',"  
            + " receberEmail = " + cliente.isReceberEmail() + ", "
            + " tipo = '" + cliente.getTipo() + "',"
            + " codCidade = "+cliente.getCidade().getId() 
            + " WHERE id = " + cliente.getId();
    conexao.executar(query);
    }
    
    public static void excluir(int idCliente){
        String query = "DELETE FROM clientes WHERE "
                + " id = " + idCliente;
        conexao.executar(query);
    }
    
    public static List<Cliente> getClientes(){
        List<Cliente> lista = new ArrayList<>();
        String query = "SELECT c.id, c.nome, c.email, "
                + " c.cpf_cnpj, c.tipo, c.receberEmail, "
                + " d.id, d.nome FROM CLIENTES C "
                + " INNER JOIN cidades d ON c.codCidade = d.id ";
        ResultSet rs =  conexao.consultar(query);
        
        if ( rs != null){
            try{
                while ( rs.next()){
                    Cidade cid = new Cidade();
                    cid.setId(rs.getInt(7));
                    cid.setNome(rs.getString(8));
                    
                    Cliente cli = new Cliente();
                    
                    cli.setCidade(cid);
                    
                    cli.setId(rs.getInt(1));
                    cli.setNome(rs.getString(2));
                    cli.setEmail(rs.getString(3));
                    
                    cli.setTipo(rs.getString(5));
                    if (rs.getInt(6) == 1){
                        cli.setReceberEmail(true);
                    }
                    
                    if (rs.getString(5).equals(Cliente.PESSOA_FISICA)){
                        ClientePF pf = (ClientePF) cli;
                        pf.setCPF(rs.getString(4));
                    }else{
                        ClientePJ pj = (ClientePJ) cli;
                        pj.setCNPJ(rs.getString(4));
                    }
                    lista.add(cli);
                } 
            }catch(Exception e){
                return null;
            }
    
        }else{
            return null;
        }
        return  lista;
     }
    
    
    
}
