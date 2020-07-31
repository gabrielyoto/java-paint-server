import java.net.*;
import java.util.*;

public class AceitadoraDeConexao extends Thread
{
    private final ServerSocket pedido;
    private final ArrayList<Parceiro> usuarios;

    public AceitadoraDeConexao(String porta, ArrayList<Parceiro> usuarios) throws Exception
    {
        if (porta == null)
            throw new Exception("Porta ausente");

        try
        {
            this.pedido = new ServerSocket(Integer.parseInt(porta));
        }
        catch (Exception erro)
        {
            throw new Exception("Porta inválida");
        }

        if (usuarios == null)
            throw new Exception("Usuários ausentes");

        this.usuarios = usuarios;
    }

    public void run()
    {
        for(;;)
        {
            Socket conexao;
            try
            {
                conexao = this.pedido.accept();
            }
            catch (Exception erro)
            {
                continue;
            }

            SupervisoraDeConexao supervisoraDeConexao;
            try
            {
                supervisoraDeConexao = new SupervisoraDeConexao(conexao, usuarios);
            }
            catch (Exception ignored)
            {
                continue;
            } // sei que passei parâmetros corretos para o construtor

            try
            {
                supervisoraDeConexao.start();
            }
            catch (IllegalThreadStateException ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
