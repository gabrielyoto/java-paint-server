package bd.dbos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Desenho implements Cloneable
{
    private String nome;
    private Date criacao;
    private Date atualizacao;
    private String ip;

    public Desenho(String nome, String criacao, String atualizacao, String ip) throws Exception
    {
        try
        {
            this.setNome(nome);
            this.setCriacao(criacao);
            this.setAtualizacao(atualizacao);
            this.setIp(ip);
        }
        catch (Exception ex)
        {
            throw new Exception(ex);
        }
    }

    public void setNome(String nome) throws Exception
    {
        if(nome == null || nome.equals(""))
            throw new Exception ("Nome inválido");

        this.nome = nome;
    }

    public void setCriacao(String criacao) throws Exception
    {
        if (criacao == null || criacao.equals(""))
            throw new Exception ("Data de criação inválida");
        try
        {
            String[] data = criacao.split("-");
            int dia = Integer.parseInt(data[2]);
            int mes = Integer.parseInt(data[1]);
            int ano = Integer.parseInt(data[0]);
            if (dia < 0 || dia > 31)
                throw new Exception("Dia inválido!");
            if (mes < 0 || mes > 12)
                throw new Exception("Mês inválido!");
            if (ano < 1970)
                throw new Exception("Ano inválido!");

            Calendar cal = Calendar.getInstance();
            cal.set(ano, (mes - 1), dia);
            this.criacao = cal.getTime();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception("Formato de data inválido! Deveria ser aaaa-MM-yyyy");
        }
    }

    public void setCriacao(Date criacao)
    {
        this.criacao = criacao;
    }

    public void setAtualizacao(String atualizacao) throws Exception
    {
        if (atualizacao == null || atualizacao.equals(""))
            throw new Exception ("Data de atualização inválida");
        try
        {
            String[] data = atualizacao.split("-");
            int dia = Integer.parseInt(data[2]);
            int mes = Integer.parseInt(data[1]);
            int ano = Integer.parseInt(data[0]);
            if (dia < 0 || dia > 31)
                throw new Exception("Dia inválido!");
            if (mes < 0 || mes > 12)
                throw new Exception("Mês inválido!");
            if (ano < 1970)
                throw new Exception("Ano inválido!");

            Calendar cal = Calendar.getInstance();
            cal.set(ano, (mes - 1), dia);
            this.atualizacao = cal.getTime();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception("Formato de data inválido! Deveria ser aaaa-MM-dd");
        }
    }

    public void setAtualizacao(Date atualizacao)
    {
        this.atualizacao = atualizacao;
    }

    public void setIp(String ip) throws Exception
    {
        if (ip == null || ip.equals(""))
            throw new Exception ("IP inválido");
        this.ip = ip;
    }

    public String getNome()
    {
        return this.nome;
    }

    public Date getCriacao()
    {
        return this.criacao;
    }

    public Date getAtualizacao()
    {
        return this.atualizacao;
    }

    public String getIp()
    {
        return this.ip;
    }

    public String toString()
    {
        String ret = "";

        ret += "Nome: " + this.nome + "\n";
        ret += "Data de Criação: " + this.criacao +"\n";
        ret += "Data de Atualização: " + this.atualizacao +"\n";
        ret += "Ip: " + this.ip + "\n";

        return ret;
    }

    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (!(obj instanceof Desenho))
            return false;

        Desenho des = (Desenho)obj;

        if (this.nome.equals(des.nome))
            return false;

        if (this.atualizacao.equals(des.atualizacao))
            return false;

        if (this.criacao.equals(des.criacao))
            return false;

        return !this.ip.equals(des.ip);
    }

    public Desenho (Desenho modelo) throws Exception
    {
        try {
            setNome(modelo.nome); // nao clono, pq nao eh objeto
            setCriacao(modelo.criacao);   // nao clono, pq nao eh clonavel
            setAtualizacao(modelo.atualizacao);  // nao clono, pq nao eh objeto
            setIp(modelo.ip);
        }
        catch (Exception ex)
        {
            throw new Exception(ex);
        }
    }


    public int hashCode ()
    {
        int ret=666;

        ret = 7 * ret + this.nome.hashCode();
        ret = 7 * ret + this.criacao.hashCode();
        ret = 7 * ret + this.atualizacao.hashCode();
        ret = 7 * ret + this.ip.hashCode();

        return ret;
    }


    public Object clone()
    {
        Desenho ret = null;

        try
        {
            ret = new Desenho (this);
        }
        catch (Exception ignored)
        {} 
        return ret;
    }
}