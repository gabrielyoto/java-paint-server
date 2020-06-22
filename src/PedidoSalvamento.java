import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import bd.*;
import bd.daos.Desenhos;

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

  public void salvar()
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
        return;
      }
    } catch (IOException ex) {
      ex.printStackTrace();
      return;
    }
    try
    {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
      String hoje = LocalDateTime.now().format(dtf);
      String ip = Inet4Address.getLocalHost().getHostAddress();
      bd.dbos.Desenho desenho = new bd.dbos.Desenho(nome, hoje, hoje, ip);
      Desenhos.inserir(desenho);
    } catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
}
