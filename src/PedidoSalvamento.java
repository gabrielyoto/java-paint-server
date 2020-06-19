import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

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
  /*  {
      File arquivo;
      File arquivoExt;
      String nome = arquivo.getName();
      if (nome.split("\\.").length <= 1 ||
          (nome.split("\\.").length > 1 && !nome.split("\\.")[1].equals("javapaint"))) {
        arquivoExt = new File(arquivo.getAbsolutePath() + ".javapaint");
      } else {
        arquivoExt = arquivo;
      }
      try {
        if (arquivoExt.createNewFile()) {
          System.out.println("Arquivo criado: " + arquivoExt.getName());
        } else {
          System.out.println("Arquivo já existe.");
          if (JOptionPane.showConfirmDialog(Janela.this, "Arquivo já existente\n" +
              "Deseja sobrescrever?", "Atenção", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION)
          return;
        }
        try {
          FileWriter escritor = new FileWriter(arquivoExt.getAbsoluteFile());
          for (Figura figura : figuras) {
            escritor.write(figura.toString() + "\n");
          }
          escritor.close();
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(Janela.this, "Erro na escrita do arquivo!");
          ex.printStackTrace();
        }
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(Janela.this, "Erro na criação do arquivo!");
        ex.printStackTrace();
      }
    }*/
  }
}
