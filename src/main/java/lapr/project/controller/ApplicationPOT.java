/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.controller;



import lapr.project.model.Platform;

/**
 *
 * @author paulomaio
 */
public class ApplicationPOT {
       
    private Platform m_oPlatform;

    private ApplicationPOT()
    {
        this.m_oPlatform = new Platform();
    }
    
    public Platform getPlatform()
    {
        return m_oPlatform;
    }
    

/*
    public UserSession getSession()
    {
        return this.m_oAutorizacao.getSession();
    }
    
    public boolean doLogin(String strId, String strPwd)
    {
       return this.m_oAutorizacao.doLogin(strId,strPwd) != null;
    }
    
    public void doLogout()
    {

        this.m_oAutorizacao.doLogout();
    }
    
    private Properties getProperties()
    {
        Properties props = new Properties();
        
        // Adiciona propriedades e valores por omissão
        props.setProperty(Constants.PARAMS_PLATFORM_DESIGNATION, "Task for Joe");

        
        // Lê as propriedades e valores definidas 
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILE);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {
            
        }
        return props;
    }
*/

    // Inspirado em https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static ApplicationPOT singleton = null;
    public static ApplicationPOT getInstance()
    {
        if(singleton == null)
        {
            synchronized(ApplicationPOT.class)
            { 
                singleton = new ApplicationPOT();
            }
        }
        return singleton;
    }

    public void setPlatform(Platform plat) {
        this.m_oPlatform = plat;
    }
    
/*
    public void addAdminsAndTestUsers(){
        this.m_oAutorizacao.addAdminsAndTestUsers();
    }

    public void setPermissionFacade(PermissionFacade permFacade) {
        this.m_oAutorizacao = permFacade;
    }
*/
}
