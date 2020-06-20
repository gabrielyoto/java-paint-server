import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

public class PedidoSalvamento extends Comunicado
{
  private static final long serialVersionUID = 6529685098267757690L;
  private final Desenho desenho;

  public PedidoSalvamento(Desenho desenho)
  {
    this.desenho = desenho;
  }

  public Desenho getDesenho()
  {
    return this.desenho;
  }

  public void salvar(Connection conexaoBD)
  {
    {
      String nome = desenho.getNome();
      File arquivo = new File("desenhos/" + nome + ".javapaint");
      try {
        if (arquivo.createNewFile()) {
          System.out.println("Arquivo criado: " + arquivo.getName());
        }
        try {
          FileWriter escritor = new FileWriter(arquivo.getAbsoluteFile());
          for (String figura : desenho.getConteudo()) {
            escritor.write(figura + "\n");
          }
          escritor.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }
}
