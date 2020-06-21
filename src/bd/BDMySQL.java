package bd;

import bd.core.*;

public class BDMySQL
{
    public static final MeuPreparedStatement COMANDO;

    static
    {
    	MeuPreparedStatement comando = null;

    	try
        {
            comando = new MeuPreparedStatement (
                      "com.mysql.jdbc.Driver",
                      "jdbc:mysql://localhost:3306/paint",
                      BDConfig.user, BDConfig.password); // criar classe BDConfig dentro desse package e setar
                                                         // user e password como variaveis estáticas lá
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
    }
}