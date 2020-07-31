import java.util.*;

public class Servidor
{
	public static String PORTA_PADRAO = "3000";
  private static String[] savedArgs;

  public static String[] getArgs() {
    return savedArgs;
  }
	
  public static void main(String[] args)
  {
    savedArgs = args;
    if (args.length > 3)
    {
      System.err.println("Uso esperado: java Servidor USUARIO_BD SENHA_BD [PORTA]\n");
      return;
    }

    String porta = Servidor.PORTA_PADRAO;

    if (args.length == 3)
      porta = args[2];

    ArrayList<Parceiro> usuarios = new ArrayList<>();

    AceitadoraDeConexao aceitadoraDeConexao;
    try
    {
      aceitadoraDeConexao = new AceitadoraDeConexao(porta, usuarios);
      aceitadoraDeConexao.start();
    }
    catch (Exception erro)
    {
      System.err.println("Escolha uma porta apropriada e liberada para uso!\n");
      return;
    }

    for(;;)
    {
      System.out.println("O servidor esta ativo! Para desativa-lo");
      System.out.println("use o comando \"desativar\"\n");
      System.out.print("> ");

      String comando = null;
      try
      {
        comando = Teclado.getUmString();
      }
      catch (Exception ignored)
      {}

      if (comando.toLowerCase().equals("desativar"))
      {
        synchronized (usuarios)
        {
          ComunicadoDeDesligamento comunicadoDeDesligamento = new ComunicadoDeDesligamento();
          for (Parceiro usuario:usuarios)
          {
            try
            {
              usuario.receba(comunicadoDeDesligamento);
              usuario.adeus();
            }
            catch (Exception ignored)
            {}
          }
        }

        System.out.println("O servidor foi desativado!\n");
        System.exit(0);
      }
      else
        System.err.println("Comando inválido!\n");
    }
  }
}
