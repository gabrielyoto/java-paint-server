package bd.daos;

import java.sql.*;

import bd.BDMySQL;
import bd.dbos.Desenho;
import bd.core.*;

public class Desenhos
{
    public static boolean existe(String nome) throws Exception
    {
        boolean retorno;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM desenho " +
                  "WHERE nome = ?";

            BDMySQL.COMANDO.prepareStatement(sql);
            BDMySQL.COMANDO.setString(1, nome);
            MeuResultSet resultado = (MeuResultSet)BDMySQL.COMANDO.executeQuery();
            retorno = resultado.first();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new Exception(ex);
        }
        return retorno;
    }

    public static void inserir(Desenho desenho) throws Exception
    {
        if(desenho == null)
            throw new Exception ("Desenho inválido");
        
        try 
        {
            String sql;
            sql = "INSERT INTO desenhos " +
                  "(nome, cliente, criacao, atualizacao)"+
                  " VALUES"+
                  " (?, ?, ?, ?)";
            
            BDMySQL.COMANDO.prepareStatement (sql);

            BDMySQL.COMANDO.setString(1, desenho.getNome());
            BDMySQL.COMANDO.setString(2, desenho.getIp());
            BDMySQL.COMANDO.setDate(3, new Date(desenho.getCriacao().getTime()));
            BDMySQL.COMANDO.setDate(4, new Date(desenho.getAtualizacao().getTime()));

            BDMySQL.COMANDO.executeUpdate();
            BDMySQL.COMANDO.commit();
        }

        catch (SQLException erro)
        {
            erro.printStackTrace();
        }
    
    }

    public static void excluir(Desenho desenho) throws Exception
    {
        if(desenho == null)
            throw new Exception ("Desenho inválido");
        
        try 
        {
            String sql;
            sql = "DELETE FROM DESENHOS "+
                  "WHERE nome = ?";

            BDMySQL.COMANDO.prepareStatement(sql);
            BDMySQL.COMANDO.setString(1, desenho.getNome());
            BDMySQL.COMANDO.executeUpdate();
            BDMySQL.COMANDO.commit();
        } 
        catch (SQLException erro) 
        {
            erro.printStackTrace();
        }
    }
}