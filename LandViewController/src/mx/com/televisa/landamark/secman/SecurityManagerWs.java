/**
* Project: Integration Landmark
*
* File: SecurityManagerWs.java
*
* Created on:  Enero 23, 2019 at 11:00
*
* Copyright (c) - Omw - 2019s
*/
package mx.com.televisa.landamark.secman;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.ws.WebServiceRef;
import javax.xml.ws.soap.SOAPFaultException;

import mx.com.televisa.landamark.client.auth.SecmanBsAutenticar;
import mx.com.televisa.landamark.client.auth.SecmanBsAutenticar_Service;
import mx.com.televisa.landamark.client.auth.types.ProcessResponse;
import mx.com.televisa.landamark.client.auth.types.UserLogin;
import mx.com.televisa.landamark.client.email.types.local.EmailDestinationAddress;
import mx.com.televisa.landamark.client.grupos.SecmanDasUsuariosGrupo;
import mx.com.televisa.landamark.client.grupos.SecmanDasUsuariosGrupoService;
import mx.com.televisa.landamark.client.grupos.types.GrupoUsuarios;
import mx.com.televisa.landamark.client.grupos.types.UsuariosCollection;
import mx.com.televisa.landamark.client.grupos.types.UsuariosGrupoInputParameters;
import mx.com.televisa.landamark.client.operations.SecmanDasOperacionesUsuario;
import mx.com.televisa.landamark.client.operations.SecmanDasOperacionesUsuarioService;
import mx.com.televisa.landamark.client.userpermission.SecmanDasUsuarioPermisos;
import mx.com.televisa.landamark.client.userpermission.SecmanDasUsuarioPermisosService;
import mx.com.televisa.landamark.client.userpermission.types.DescUsuario;
import mx.com.televisa.landamark.client.userpermission.types.PermisoOutputCollection;
import mx.com.televisa.landamark.client.userpermission.types.PermisosUsuarioInputParameters;
import mx.com.televisa.landamark.client.userpermission.types.Usuario;
import mx.com.televisa.landamark.util.UtilFaces;

/** Clase que hace uso de los servicios de Security Manager <br/>
 *
 * @author Jorge Luis Bautista - Omw
 *
 * @version 01.00.01
 *
 * @date Enero 23, 2019, 12:00 pm
 */
public class SecurityManagerWs {
    public SecurityManagerWs() {
        super();
    }
    /**
     * Valida los permisos del usuario en la aplicacion 
     * Aplicacion de Integracion con landmark
     * @autor Jorge Luis Bautista Santiago
     * @param tsUserName
     * @param toApplication
     * @return void
     */
    @WebServiceRef
    private static SecmanDasUsuarioPermisosService loSmnUserPermissionService;
    
    public Usuario getSecmanUserDataSession(String tsUserName,
                                            String toApplication){
        Usuario loSecmanUser = new Usuario();
        loSmnUserPermissionService = new SecmanDasUsuarioPermisosService();
        try{
    
            SecmanDasUsuarioPermisos       loSmsUserPermission = 
                loSmnUserPermissionService.getSecmanDasUsuarioPermisosPort();
                
            PermisosUsuarioInputParameters loUserInput = new PermisosUsuarioInputParameters();
            loUserInput.setNomAplicacion(toApplication);
            loUserInput.setUserName(tsUserName);
            PermisoOutputCollection        loUserOutput = 
                loSmsUserPermission.obtenerRolTareaPermisos(loUserInput);
            loSecmanUser = loUserOutput.getUsuario();
        }catch(Exception loEx){
            System.out.println("ERROR secman 9083 " + loEx.getMessage());
            loSecmanUser = null;
        }
        
        return loSecmanUser;
    }
    
    /**
     * Valida en Security Manager si el usuario y contraseña son permitidos en la
     * Aplicacion
     * @autor Jorge Luis Bautista Santiago
     * @param tsUser
     * @param tsPassword
     * @param tsApplicationName
     * @return String
     */
    public String loginSecurityManager(String tsUser, 
                                       String tsPassword, 
                                       String tsApplicationName
                                      ) throws Exception {
        String    lsToken = null;
        String    lsUser = "";
        String    lsPassword = "";
               
        UtilFaces loUf = new UtilFaces();
        try {
            String lsKey= loUf.getKeyDecoder();            
            lsUser = loUf.encryptObject(tsUser,lsKey);
            lsPassword = loUf.encryptObject(tsPassword, lsKey);
            //System.out.println("lsKey: "+lsKey);
            //System.out.println("lsUser(encrypt): "+lsUser);
            //System.out.println("lsPassword(encrypt): "+lsPassword);
        } catch (Exception loEx) {
            lsToken = null;
        }
        SecmanBsAutenticar_Service loSecmanBsAuthService = 
            new SecmanBsAutenticar_Service();
    
        try{
            SecmanBsAutenticar         loSecmanBsAuth = 
                loSecmanBsAuthService.getSecmanBsAutenticarSoap12HttpPort();
            UserLogin                  loUserLogin = new UserLogin();
            loUserLogin.setUserName(lsUser);
            loUserLogin.setUserPassword(lsPassword);
            loUserLogin.setNomAplicacion(tsApplicationName);
            loUserLogin.setAccion("login");          
            loUserLogin.setToken("");
            ProcessResponse            loProcessResponse;
            loProcessResponse = loSecmanBsAuth.autenticarUsuario(loUserLogin);
            if (loProcessResponse.getResult().startsWith("FAILED")){    
                throw new Exception (loProcessResponse.getError());
            }
            else{
                lsToken = loProcessResponse.getResult();                
            }    
        }catch(SOAPFaultException loEx){
            ;
        } catch (Exception loEx) {
            throw new Exception (loEx.getMessage());
        }                        
        return lsToken;
    }
    
    /**
     * Valida en Security Manager si el usuario y contraseña son permitidos en la
     * Aplicacion
     * @autor Jorge Luis Bautista Santiago
     * @param tsUser
     * @param psPassword
     * @param tsApplicationName
     * @return void
     */
    public void logoutSecurityManager(String tsUser, 
                                      String tsApplicationName
                                      ) throws Exception {    
        String    lsUser = "";
        UtilFaces loUf = new UtilFaces();
        try {
            String lsKey= loUf.getKeyDecoder();
            lsUser = loUf.encryptObject(tsUser,lsKey);
        } catch (Exception loEx) {
            ;
        }

        SecmanBsAutenticar_Service loSecmanBsAuthService = 
            new SecmanBsAutenticar_Service();
        try{
            SecmanBsAutenticar     loSecmanBsAuth = 
                loSecmanBsAuthService.getSecmanBsAutenticarSoap12HttpPort();
            UserLogin              loUserLogin = new UserLogin();
            loUserLogin.setUserName(lsUser);
            loUserLogin.setNomAplicacion(tsApplicationName);
            loUserLogin.setAccion("logout");          
            loUserLogin.setToken("");
            ProcessResponse        loProcessResponse;
            loProcessResponse = loSecmanBsAuth.autenticarUsuario(loUserLogin);
            if (loProcessResponse.getResult().startsWith("FAILED")){                
                throw new Exception (loProcessResponse.getError());
            }   
        }catch(SOAPFaultException loEx){
            ;
        } catch (Exception loEx) {
            throw new Exception (loEx.getMessage());
        }                        
    }
   
    /**
     * Devuelve la lista de operaciones que tiene el usuario en la aplicacion solicitada
     * @autor Jorge Luis Bautista Santiago
     * @param tsUserName
     * @param tsApplication
     * @return List
    */  
    @WebServiceRef
    private static SecmanDasOperacionesUsuarioService poSecmanDasOperacionesUsuarioService;    
    public List<String> getUserOperations(String tsUserName, 
                                          String tsApplication
                                          ) {
        List<String> laOperations = new ArrayList<String>();
        try {
            poSecmanDasOperacionesUsuarioService = 
                new SecmanDasOperacionesUsuarioService();
            SecmanDasOperacionesUsuario              loSecmanDasOperacionesUsuario = 
                poSecmanDasOperacionesUsuarioService.getSecmanDasOperacionesUsuarioPort();
            
            mx.com.televisa.landamark.client.operations.types.PermisosUsuarioInputParameters     loUserPermissions = 
                new mx.com.televisa.landamark.client.operations.types.PermisosUsuarioInputParameters();
            
            loUserPermissions.setNomAplicacion(tsApplication);
            loUserPermissions.setUserName(tsUserName);
            
            mx.com.televisa.landamark.client.operations.types.OperacionesUsuarioOutputParameters loUserOperations = 
                loSecmanDasOperacionesUsuario.obtenerOperacionesUsuario(loUserPermissions);
            mx.com.televisa.landamark.client.operations.types.OperacionesCollection              loOpCollection = 
                loUserOperations.getOperacionesCollection();
            Iterator                                                                                     loIterator;
            loIterator = loOpCollection.getOperacion().iterator();
            int                                                                                          liI = 0;
            while (loIterator.hasNext()) {
                mx.com.televisa.landamark.client.operations.types.Operacion psOperacion = 
                    (mx.com.televisa.landamark.client.operations.types.Operacion)loIterator.next();
                laOperations.add(psOperacion.getOperacionName().getOperacionName());
                liI++;
            }
        } catch (Exception loEx) {
            System.out.println ("Error al obtener Operaciones "+loEx.getMessage());
            ;
        }
        return laOperations;
    } 
    
    /**
    * Obtiene la lista de direcciones de correos electrónicos configurados en secman
    * respecto al grup de usuarios
    * @autor Jorge Luis Bautista Santiago
    * @param tsGroupName
    * @return List
    */
    public List<EmailDestinationAddress> getListEmailAddressByGroup(String tsGroupName){
        System.out.println("Dentro de getListEmailAddressByGroup");
        List<EmailDestinationAddress> laEmailList = new ArrayList<EmailDestinationAddress>();
        SecmanDasUsuariosGrupoService    loSecmanDasUsuariosGrupoService = new SecmanDasUsuariosGrupoService();
        SecmanDasUsuariosGrupo loSecmanDasUsuariosGrupo = 
            loSecmanDasUsuariosGrupoService.getSecmanDasUsuariosGrupoPort();
        UsuariosGrupoInputParameters loUgp = new UsuariosGrupoInputParameters();
        loUgp.setNomGrupo(tsGroupName);
        UsuariosCollection loUsrCol = 
        loSecmanDasUsuariosGrupo.obtenerUsuariosGrupo(loUgp);
        if(loUsrCol.getUsuarios().size() > 0){
            for(GrupoUsuarios loGpoUsers : loUsrCol.getUsuarios()){
                if(loGpoUsers.getMailUsuario() != null){
                    EmailDestinationAddress loBean = new EmailDestinationAddress();
                    loBean.setLsAddressTo(loGpoUsers.getMailUsuario());
                    loBean.setLsNameTo(loGpoUsers.getMailUsuario());
                    laEmailList.add(loBean);
                }
            }
            
        }   
        return laEmailList; 
    }
   
}
