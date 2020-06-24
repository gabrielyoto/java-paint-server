import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    try {
      if (Desenhos.existe(nome)) {
        atualizar();
        return;
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    File arquivo = new File("desenhos/" + nome + ".javapaint");
    try {
      arquivo.createNewFile();
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
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      String hoje = LocalDateTime.now().format(dtf);
      bd.dbos.Desenho desenho = new bd.dbos.Desenho(nome, hoje, hoje, this.desenho.getIp());
      Desenhos.inserir(desenho);
    } catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }

  public void atualizar() throws Exception
  {
    String nome = desenho.getNome();
    File arquivo = new File("desenhos/" + nome + ".javapaint");
    try {
      if (arquivo.createNewFile()) throw new Exception("Arquivo não encontrado!");
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
    try {
      Desenhos.atualizar(new bd.dbos.Desenho(
          nome,
          desenho.getDataCriacao(),
          desenho.getDataUltimaAtualizacao(),
          desenho.getIp()
      ));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
