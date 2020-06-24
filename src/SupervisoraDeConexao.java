import java.io.*;
import java.net.*;
import java.util.*;

import bd.core.MeuResultSet;
import bd.daos.Desenhos;

import javax.swing.*;

public class SupervisoraDeConexao extends Thread
{
    private Parceiro usuario;
    private final Socket conexao;
    private final ArrayList<Parceiro> usuarios;

    public SupervisoraDeConexao (Socket conexao, ArrayList<Parceiro> usuarios) throws Exception
    {
        if (conexao == null)
            throw new Exception("Conexão ausente");

        if (usuarios == null)
            throw new Exception("Usuários ausentes");

        this.conexao = conexao;
        this.usuarios = usuarios;
    }

    public void run ()
    {
        ObjectOutputStream transmissor;
        try
        {
            transmissor = new ObjectOutputStream(this.conexao.getOutputStream());
        }
        catch (Exception erro)
        {
            erro.printStackTrace();
            return;
        }

        ObjectInputStream receptor;
        try
        {
            receptor = new ObjectInputStream(this.conexao.getInputStream());
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close();
            }
            catch (Exception ignored)
            {} // so tentando fechar antes de acabar a thread
            return;
        }

        try
        {
            this.usuario = new Parceiro(this.conexao, receptor, transmissor);
        }
        catch (Exception ignored)
        {} // sei que passei os parâmetros corretos

        try
        {
            synchronized (this.usuarios)
            {
                this.usuarios.add(this.usuario);
            }

            for(;;)
            {
                Comunicado comunicado = this.usuario.envie();

                if (comunicado == null)
                    return;
                else if (comunicado instanceof PedidoSalvamento) {
                    String nome = desenho.getNome();
                        if(nome.equals(ArrayList<String>desenhos(getDesenhos()))) {
                         String atualizacao = desenho;
                         atualizar(Desenho atualizacao);
                        } 
                        else((PedidoSalvamento) comunicado).salvar();                 
                }
                else if (comunicado instanceof PedidoDesenhos) {
                    MeuResultSet resultado = Desenhos.listar();
                    ArrayList<String> desenhos = new ArrayList<>();
                    while (resultado.next())
                    {
                        desenhos.add(resultado.getString("nome"));
                    }
                    usuario.receba(new ListaDesenhos(desenhos));
                }
                else if (comunicado instanceof PedidoDesenho) {
                    bd.dbos.Desenho desenhoBD = Desenhos.getDesenho(((PedidoDesenho)comunicado).getNome());
                    Desenho desenho = new Desenho(
                        desenhoBD.getNome(),
                        desenhoBD.getCriacao(),
                        desenhoBD.getAtualizacao()
                    );
                    File selectedFile = new File("desenhos/" + desenhoBD.getNome() + ".javapaint");
                    try {
                        Scanner leitor = new Scanner(selectedFile);
                        while (leitor.hasNextLine()) {
                            String figura = leitor.nextLine();
                            desenho.addFigura(figura);
                        }
                        leitor.close();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    usuario.receba(desenho);
                }
                else if (comunicado instanceof PedidoParaSair) {
                    transmissor.close();
                    receptor.close();
                    return;
                }
            }
        }
        catch (Exception erro)
        {
            try
            {
                transmissor.close();
                receptor.close();
            }
            catch (Exception ignored)
            {} // so tentando fechar antes de acabar a thread
        }
    }
}
