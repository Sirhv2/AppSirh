package co.gov.ideam.sirh.seguridad.business;


import co.gov.ideam.sirh.remoto.business.RegistroRemotoLocal;
import co.gov.ideam.sirh.mail.IdeamMail;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.seguridad.dao.SeguridadDAO;
import co.gov.ideam.sirh.seguridad.model.Funcionario;
import co.gov.ideam.sirh.seguridad.model.MenuVO;
import co.gov.ideam.sirh.seguridad.model.OpcionVO;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.model.UsuarioVO;
import co.gov.ideam.sirh.seguridad.util.EncrypClass;
import co.gov.ideam.sirh.seguridad.util.PasswordGenerator;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;


import java.sql.SQLException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;

import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceProperty;
import javax.persistence.Query;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;

/**
 * EJB del modulo de seguridad, se encarga de realizar el mapeo con las
 * diferentes tablas de la base de datos a traves de PersistenceContext
 */
@Stateless(mappedName = "Seguridad", name = "SeguridadBean")
public class SeguridadEJBBean implements SeguridadEJB {

    private static final String TEXTO_MAIL_CLAVE[] =
    { "Cordial saludo, Se ha asignado la siguiente clave ",
      " en el sistema \"SIRH\" para el usuario ",
      ". Recuerde que DEBE modificarla cuando ingrese al sistema a traves del menu Opciones Generales >> Cambiar Clave.",
      "  Cordialmente, ", "  Equipo de trabajo del SIRH ",
      "  soporte_sirh@ideam.gov.co " };

    @PersistenceContext(unitName = "SirhPU")
    private EntityManager em;
    @EJB
    private RegistroRemotoLocal registroRemoto;
    @EJB
    private ParametrosEJB parametrosService;

    public SeguridadEJBBean() {
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteUser(UsuarioVO usuario) throws IdeamException {

        // Borrar los perfiles del usuario
        Query q1 = em.createNamedQuery("deleteperfilesxusuario");
        q1.setParameter("codigoUsuario", usuario.getCodigo());
        q1.executeUpdate();
        
        // 20160909 AFCC: Se evita el borrado de la BD y se implementa un borrado lógico.
        /*UsuarioVO user = this.getUsuario(usuario.getLogin());
        em.remove(user);
        registroRemoto.registrarEvento(this.getClass().getName(), "deleteUser",
                                       usuario);*/
        Query q =
            em.createNativeQuery("update SIRH_USUARIOS set USU_ESTADO_REGISTRO = '1' " +
                                 "where usu_codigo = ?1");
        q.setParameter(1, usuario.getCodigo());
    }

    private void sendEmailPassword(UsuarioVO usuario,
                                   String password) throws IdeamException {
        String texto =
            TEXTO_MAIL_CLAVE[0] + password + TEXTO_MAIL_CLAVE[1] + usuario.getLogin() +
            TEXTO_MAIL_CLAVE[2] + TEXTO_MAIL_CLAVE[3] + TEXTO_MAIL_CLAVE[4] +
            TEXTO_MAIL_CLAVE[5];
        IdeamMail mail =
            new IdeamMail(texto, usuario.getEmail(), "Asignacion de Clave - SIRH");
        mail.send();
    }

    public void restartPassword(UsuarioVO usuario) throws IdeamException {
        Query q =
            em.createNativeQuery("update SIRH_USUARIOS set " + "USU_PASSWORD = ?1, " +
                                 "USU_REQUIERE_CAMBIO_CLAVE = 1 " +
                                 "where usu_codigo = ?2");
        String password = PasswordGenerator.generate();
        //password = "pwd12345";
        String claveEncriptada = EncrypClass.encript(password);
        q.setParameter(1, claveEncriptada);
        q.setParameter(2, usuario.getCodigo());
        q.executeUpdate();
        this.sendEmailPassword(usuario, password);
    }

    public void savePassword(UsuarioVO usuario,
                             String password) throws IdeamException {
        this.savePassword(usuario, password, false);
    }

    private void savePassword(UsuarioVO usuario, String password,
                              boolean requiereCambioclave) throws IdeamException {
        Query q =
            em.createNativeQuery("update SIRH_USUARIOS set " + "USU_PASSWORD = ?1, " +
                                 "USU_REQUIERE_CAMBIO_CLAVE = ?2 " +
                                 "where usu_codigo = ?3");
        String claveEncriptada = EncrypClass.encript(password);
        q.setParameter(1, claveEncriptada);
        Integer cambio = new Integer("0");
        if (requiereCambioclave)
            cambio = new Integer("1");
        q.setParameter(2, cambio);
        q.setParameter(3, usuario.getCodigo());
        q.executeUpdate();
        this.sendEmailPassword(usuario, password);
    }

    public boolean validatePassword(UsuarioVO usuario,
                                    String password) throws IdeamException {
        Query q =
            em.createNativeQuery("select USU_PASSWORD " + "from SIRH_USUARIOS " +
                                 "where usu_codigo = ?1");
        q.setParameter(1, usuario.getCodigo());
        String claveEncriptada = EncrypClass.encript(password);
        String passwordRegistrado = (String)q.getSingleResult();
        return passwordRegistrado.equals(claveEncriptada);
    }


    public boolean validarClave(UsuarioVO usuario,
                                String password) throws IdeamException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(password);
        String claveEncriptada = EncrypClass.encript(password);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(claveEncriptada);
        String sql =
            "select * from SIRH_USUARIOS where UPPER(USU_LOGIN)= ?1  AND  USU_PASSWORD = ?2";
        Query q = em.createNativeQuery(sql, UsuarioVO.class);
        q.setParameter(1, usuario.getLogin().toUpperCase());
        q.setParameter(2, claveEncriptada);
        List lista = q.getResultList();

        if (lista.size() == 0) {
            return false;
        } else if (lista != null || lista.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void updateUser(UsuarioVO usuario) throws IdeamException {
        long codigoUsuario = 0;
        String password = "";
        usuario.setFechaModificacion(Calendar.getInstance());
        
        if (usuario.getCodigo() == 0) {
            usuario.setRequiereCambioClave(1);
            password = PasswordGenerator.generate();
            String claveEncriptada = EncrypClass.encript(password);
            usuario.setPassword(claveEncriptada);
            em.persist(usuario);
            codigoUsuario = usuario.getCodigo();

            em.flush();
            // Borrar los perfiles del usuario

            Query q = em.createNamedQuery("deleteperfilesxusuario");
            q.setParameter("codigoUsuario", usuario.getCodigo());
            q.executeUpdate();

            // Registrar los nuevos perfiles
            if (usuario.getPerfilesAsociados() != null) {
                Iterator it = usuario.getPerfilesAsociados().iterator();
                while (it.hasNext()) {
                    PerfilVO p = (PerfilVO)it.next();
                    q = em.createNamedQuery("insertperfilesxusuario");
                    q.setParameter("codigoPerfil", p.getCodigo());
                    q.setParameter("codigoUsuario", codigoUsuario);
                    q.executeUpdate();
                }
            }
            this.sendEmailPassword(usuario, password);

        } else {

            usuario.setRequiereCambioClave(1);
            password = PasswordGenerator.generate();
            String claveEncriptada = EncrypClass.encript(password);
            usuario.setPassword(claveEncriptada);

            em.merge(usuario);
            em.flush();

            this.sendEmailPassword(usuario, password);
        }


    }

    public boolean validateUser(String login,
                                String clave) throws IdeamException {
        try {
            Query q =
                em.createQuery("select x from co.gov.ideam.sirh.seguridad.model.UsuarioVO x where x.login = ?1");
            q.setParameter(1, login);
            Object obj = q.getSingleResult();
            if (obj != null) {
                UsuarioVO user = (UsuarioVO)obj;
                if (user.isActivo() == 1) {
                    boolean valido = this.validatePassword(user, clave);
                    if (valido) {
                        user.setUltimoIngresoExitoso(GregorianCalendar.getInstance());
                        em.merge(user);
                        return true;
                    } else {
                        user.setUltimoIngresoNoExitoso(GregorianCalendar.getInstance());
                        em.merge(user);
                        return false;
                    }
                } else {
                    user.setUltimoIngresoNoExitoso(GregorianCalendar.getInstance());
                    em.merge(user);
                    return false;
                }
            }
        } catch (NoResultException e) {
            return false;
        }
        return false;
    }


    public int autenticarUser(String login,
                              String clave) throws IdeamException {
        try {

            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Autenticando login: "+login);
            System.out.println("Autenticando clave: "+login);

            String sql =
                "select * from sirh_usuarios u  where u.usu_login = ?1";
            Query q = em.createNativeQuery(sql, UsuarioVO.class);
            q.setParameter(1, login);

            UsuarioVO user = new UsuarioVO();
            List lista = q.getResultList();

            if (lista.size() == 0) {

                return 3;
            } else if (lista != null || lista.size() > 0) {

            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("if (lista != null || lista.size() > 0)");


                user = (UsuarioVO)lista.get(0);


                if (user.isActivo() == 1) {
                

                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                    System.out.println(" if (user.isActivo() == 1)");
                    boolean valido = false;
                    valido = this.validarClave(user, clave);


                    if (valido) {

                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("  if (valido) {");
                        user.setUltimoIngresoExitoso(GregorianCalendar.getInstance());
                        em.merge(user);
                        if (user.isRequiereCambioClave() == 1)
                            return 4;
                        else
                            return 0;
                    } else {
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("  else");
                        user.setUltimoIngresoNoExitoso(GregorianCalendar.getInstance());
                        em.merge(user);
                        return 1;
                    }
                } else {
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("  else");
                    user.setUltimoIngresoNoExitoso(GregorianCalendar.getInstance());
                    em.merge(user);
                    return 2;
                }
            } else {
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                        System.out.println("  else");
                return 3;
            }

        } catch (NoResultException e) {
            return 9999;
        }

    }


    public UsuarioVO getUsuarioByEmail(String email) throws IdeamException {
        try {
            Query q =
                em.createQuery("select x from co.gov.ideam.sirh.seguridad.model.UsuarioVO x where x.email = ?1");
            q.setParameter(1, email);
            Object obj = q.getSingleResult();
            UsuarioVO usuario = (UsuarioVO)obj;
            this.loadAttributes(usuario);
            return usuario;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.flush();
        }
    }

    public UsuarioVO getUsuario(String login) throws IdeamException {
        try {
            Query q =
                em.createQuery("select x from co.gov.ideam.sirh.seguridad.model.UsuarioVO x where x.login = ?1");
            q.setParameter(1, login);
            Object obj = q.getSingleResult();
            UsuarioVO usuario = (UsuarioVO)obj;
            this.loadAttributes(usuario);
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    public List getMenu(UsuarioVO usuario) throws IdeamException {
        String query =
            "    select distinct m.* " + "from SIRH_MENU m,  SIRH_MENU_GRUPO mg, SIRH_USUARIOS_GRUPOS ug, SIRH_GRUPOS g, SIRH_USUARIOS u " +
            "where m.menu_codigo = mg.megr_menu_codigo " +
            "and   ug.usgr_gprs_codigo = mg.megr_grps_codigo " +
            "and   g.grps_codigo = mg.megr_grps_codigo " +
            "and   g.grps_codigo = ug.usgr_gprs_codigo " +
            "and   ug.usgr_usu_codigo = u.usu_codigo " +
            "and   g.grps_estado = 1 " + "and   m.menu_estado = 1 " +
            "and   u.usu_estado = 1 " + "and   u.usu_codigo = ?1 ";

        String queryNodosPrincipales = query + "and m.menu_padre is null ";
        Query q =
            em.createNativeQuery(queryNodosPrincipales + " order by m.menu_orden asc",
                                 MenuVO.class);
        q.setParameter(1, usuario.getCodigo());
        List lista = q.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                MenuVO menu = (MenuVO)it.next();
                List subMenu = this.getSubMenu(query, usuario, menu);
                menu.setMenuVOList(subMenu);
            }
        }
        em.flush();
        return lista;
    }

    private List getSubMenu(String query, UsuarioVO usuario,
                            MenuVO menu) throws IdeamException {
        String queryNodosPrincipales = query + "and m.menu_padre = ?2";
        Query q =
            em.createNativeQuery(queryNodosPrincipales + " order by m.menu_orden asc",
                                 MenuVO.class);
        q.setParameter(1, usuario.getCodigo());
        q.setParameter(2, menu.getCodigo());
        List lista = q.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                MenuVO hijo = (MenuVO)it.next();
                List subMenu = this.getSubMenu(query, usuario, hijo);
                if (subMenu != null) {
                    hijo.setMenuVOList(subMenu);
                }
            }
        }
        em.flush();
        return lista;
    }

    public HashMap getMenuAutorizado(UsuarioVO usuario) throws IdeamException {
        HashMap menuAutorizado = new HashMap();
        HashMap opcionesAutorizadas = new HashMap();
        // Crear el map con los menus del usuario
        Query q = em.createNamedQuery("menuAutorizado");
        q.setParameter("codigoUsuario", usuario.getCodigo());
        List lista = q.getResultList();
        if (lista != null) {
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                MenuVO m = (MenuVO)it.next();
                menuAutorizado.put(m.getNombreClase(), new Boolean(true));
                // Cargar las opciones autorizadas de este menu
                Query qo = em.createNamedQuery("opcionesAutorizadas");
                qo.setParameter("codigoUsuario", usuario.getCodigo());
                qo.setParameter("codigoMenu", m.getCodigo());
                List listaOpciones = qo.getResultList();
                if (listaOpciones != null) {
                    Iterator ito = listaOpciones.iterator();
                    while (ito.hasNext()) {
                        OpcionVO opcion = (OpcionVO)ito.next();
                        String texto =
                            m.getNombreClase() + "_" + opcion.getOpcIdJsf();
                        //System.out.println("cargando " + texto);
                        opcionesAutorizadas.put(texto, new Boolean(true));
                    }
                }
            }
        }
        em.flush();
        return opcionesAutorizadas;
    }

    public UsuarioConectadoTO login(String loginUser) throws IdeamException {
        UsuarioConectadoTO usuarioConectado = new UsuarioConectadoTO();
        UsuarioVO usuario = this.getUsuario(loginUser);
        usuarioConectado.setUsuario(usuario);
        List menu = this.getMenu(usuario);
        /*System.out.println("Examinando menu -------------->");
        Iterator it3 = menu.iterator();
        while(it3.hasNext()){
            MenuVO m = (MenuVO)it3.next();
            System.out.println("menu " + m.getNombre());
            if (m.getMenuVOList()!=null){
                Iterator it2 = m.getMenuVOList().iterator();
                while(it2.hasNext()){
                    MenuVO m2 = (MenuVO)it2.next();
                    System.out.println("menu2 " + m2.getNombre());
                }
            }
        }*/
        usuarioConectado.setMenu(menu);
        return usuarioConectado;
    }

    public List getUsuarios(Autoridades autoridad) throws IdeamException {
        List lista = null;
        if (autoridad.getId().intValue() == Constantes.IDEAM) {
            lista =
                    em.createQuery("select x from co.gov.ideam.sirh.seguridad.model.UsuarioVO  x where x.estadoRegistro = '0'").getResultList();
        } else {
            Query query =
                em.createQuery("select x from co.gov.ideam.sirh.seguridad.model.UsuarioVO  x where x.estadoRegistro = '0' and x.codigoAutoridadAmbiental = ?1");
            query.setParameter(1, autoridad.getId().intValue());
            lista = query.getResultList();
        }
        em.flush();
        Iterator it = lista.iterator();
        while (it.hasNext()) {
            UsuarioVO usuario = (UsuarioVO)it.next();
            this.loadAttributes(usuario);
        }
        return lista;
    }

    private void loadAttributes(UsuarioVO usuario) throws IdeamException {
        if (usuario.getCodigoAutoridadAmbiental() != null) {
            Autoridades autoridad =
                parametrosService.getAutoridad(usuario.getCodigoAutoridadAmbiental());
            if (autoridad != null) {
                usuario.setAutoridadAmbiental(autoridad);
            }
        }
        
      Integer idTipoDocuemnto = usuario.getCodtipoDocumento();
      if (idTipoDocuemnto != null) {
          Lista tipoDocumento = parametrosService.getLista(idTipoDocuemnto);
          usuario.setListaTipoDoc(tipoDocumento);
      }


    }

    public List getUsuarios(UsuarioVO usuario, boolean useEstado,
                            Calendar fechaFinalIngresoExistoso,
                            Calendar fechaFinalIngresoNoExitoso) throws IdeamException {
        try {
            String sql =
                "select x from co.gov.ideam.sirh.seguridad.model.UsuarioVO x where ";
            boolean criteriosCargados = false;
            if (usuario.getLogin() != null &&
                usuario.getLogin().length() > 0) {
                if (criteriosCargados) {
                    sql += " and ";
                }
                sql +=
" upper(x.login) like '%" + usuario.getLogin().toUpperCase() + "%'";
                criteriosCargados = true;
            }
            if (usuario.getNombres() != null &&
                usuario.getNombres().length() > 0) {
                if (criteriosCargados) {
                    sql += " and ";
                }
                sql +=
" upper(x.nombres) like '%" + usuario.getNombres().toUpperCase() + "%'";
                criteriosCargados = true;
            }
            if (usuario.getApellidos() != null &&
                usuario.getApellidos().length() > 0) {
                if (criteriosCargados) {
                    sql += " and ";
                }
                sql +=
" upper(x.apellidos) like '%" + usuario.getApellidos().toUpperCase() + "%'";
                criteriosCargados = true;
            }
            if (usuario.getEmail() != null &&
                usuario.getEmail().length() > 0) {
                if (criteriosCargados) {
                    sql += " and ";
                }
                sql +=
" upper(x.email) like '%" + usuario.getEmail().toUpperCase() + "%'";
                criteriosCargados = true;
            }
            if (useEstado) {
                if (criteriosCargados) {
                    sql += " and ";
                }
                if (usuario.isActivo() == 1) {
                    sql += " x.activo = 1";
                } else {
                    sql += " x.activo <> 1";
                }
                criteriosCargados = true;
            }

            if (usuario.getUltimoIngresoExitoso() != null) {
                if (criteriosCargados) {
                    sql += " and ";
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sql +=
" x.ultimoIngresoExitoso >= to_date('" + sdf.format(usuario.getUltimoIngresoExitoso().getTime()) +
 " 00:00:00','dd/mm/yyyy HH24:MI:SS')";
                criteriosCargados = true;
            }

            if (fechaFinalIngresoExistoso != null) {
                if (criteriosCargados) {
                    sql += " and ";
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sql +=
" x.ultimoIngresoExitoso <= to_date('" + sdf.format(fechaFinalIngresoExistoso.getTime()) +
 " 23:59:59','dd/mm/yyyy HH24:MI:SS')";
                criteriosCargados = true;
            }

            if (usuario.getUltimoIngresoNoExitoso() != null) {
                if (criteriosCargados) {
                    sql += " and ";
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sql +=
" x.ultimoIngresoNoExitoso >= to_date('" + sdf.format(usuario.getUltimoIngresoNoExitoso().getTime()) +
 " 00:00:00','dd/mm/yyyy HH24:MI:SS')";
                criteriosCargados = true;
            }

            if (fechaFinalIngresoNoExitoso != null) {
                if (criteriosCargados) {
                    sql += " and ";
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sql +=
"x.ultimoIngresoNoExitoso <= to_date('" + sdf.format(fechaFinalIngresoNoExitoso.getTime()) +
 " 23:59:59','dd/mm/yyyy HH24:MI:SS')";
                criteriosCargados = true;
            }
            
            sql += " and x.estadoRegistro = '0'";

            Query q = em.createQuery(sql);
            List lista = q.getResultList();
            Iterator it = lista.iterator();
            while (it.hasNext()) {
                UsuarioVO us = (UsuarioVO)it.next();
                this.loadAttributes(us);
            }
            return lista;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.flush();
        }
    }

    public List getAllPerfiles() throws IdeamException {
        List lista =
            em.createQuery("select x from co.gov.ideam.sirh.seguridad.model.PerfilVO  x").getResultList();
        em.flush();
        return lista;
    }

    public PerfilVO getPerfil(long codigo) throws IdeamException {
        try {
            Query q =
                em.createQuery("select x from co.gov.ideam.sirh.seguridad.model.PerfilVO x where x.codigo = ?1");
            q.setParameter(1, codigo);
            Object obj = q.getSingleResult();
            return (PerfilVO)obj;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.flush();
        }
    }


    public PerfilVO getPerfilUsuario(long codigo) throws IdeamException {
        try {
            Query q = em.createNamedQuery("perfilesxusuario");
            q.setParameter("codigoUsuario", codigo);
            List result = q.getResultList();
            if (result != null) {
                return (PerfilVO)result.get(0);
            } else {
                return null;
            }
        } catch (NoResultException e) {
            return null;
        }
    }

    public void updatePerfil(PerfilVO perfil) throws IdeamException {
        em.merge(perfil);
        em.flush();
    }

    public List getPerfiles(UsuarioVO usuario) throws IdeamException {
        Query q = em.createNamedQuery("perfilesxusuario");
        q.setParameter("codigoUsuario", usuario.getCodigo());
        List lista = q.getResultList();
        em.flush();
        return lista;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletePerfil(PerfilVO perfil) throws IdeamException {
        try {
            Query q = em.createNamedQuery("deleteopcionxgrupo");
            q.setParameter("codigoPerfil", perfil.getCodigo());
            q.executeUpdate();

            // Borrar los menus asociados al perfil
            q = em.createNamedQuery("deletemenuxgrupo");
            q.setParameter("codigoPerfil", perfil.getCodigo());
            q.executeUpdate();

            // Borrar las relaciones del perfil con los usuarios
            q = em.createNamedQuery("deleteperfilfromusuarios");
            q.setParameter("codigoPerfil", perfil.getCodigo());
            q.executeUpdate();

            Object obj = em.getReference(PerfilVO.class, perfil.getCodigo());
            em.remove((PerfilVO)obj);
        } catch (Exception e) {
            throw new IdeamException(e);
        } finally {
            em.flush();
        }
    }

    public List getAllMenu() throws IdeamException {
        List lista =
            em.createQuery("select x from MenuVO  x where x.menuVO is null").getResultList();
        em.flush();
        return lista;
    }

    private MenuVO getMenu(long codigo) throws IdeamException {
        try {
            Query q =
                em.createQuery("select x from MenuVO x where x.codigo = ?1");
            q.setParameter(1, codigo);
            Object obj = q.getSingleResult();
            return (MenuVO)obj;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.flush();
        }
    }

    public MenuVO getMenuByPage(String pagina) throws IdeamException {
        try {
            Query q =
                em.createQuery("select x from MenuVO x where x.pagina = ?1");
            q.setParameter(1, pagina);
            Object obj = q.getSingleResult();
            return (MenuVO)obj;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.flush();
        }
    }

    public MenuVO getMenuByClassName(String nombreClase) throws IdeamException {
        try {
            Query q =
                em.createQuery("select x from MenuVO x where x.nombreClase = ?1");
            q.setParameter(1, nombreClase);
            Object obj = q.getSingleResult();
            return (MenuVO)obj;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.flush();
        }
    }

    public MenuVO getMenuByAction(String action) throws IdeamException {
        try {
            Query q =
                em.createQuery("select x from MenuVO x where x.accion = ?1");
            q.setParameter(1, action);
            Object obj = q.getSingleResult();
            return (MenuVO)obj;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.flush();
        }
    }

    public List<MenuVO> getMenuByPerfil(PerfilVO perfil) throws IdeamException {
        try {
            Query q = em.createNamedQuery("menuxgrupo");
            q.setParameter("codigoPerfil", perfil.getCodigo());
            List lista = q.getResultList();
            return lista;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.flush();
        }
    }

    public List<MenuVO> getOpcionByPerfil(PerfilVO perfil) throws IdeamException {
        try {
            Query q = em.createNamedQuery("opcionxgrupo");
            q.setParameter("codigoPerfil", perfil.getCodigo());
            List lista = q.getResultList();
            return lista;
        } catch (NoResultException e) {
            return null;
        } finally {
            em.flush();
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void updatePermisosPerfil(PerfilVO perfil, List listaMenu,
                                     List listaOpciones) throws IdeamException {
        try {
            //em.getTransaction().begin();
            // Borrar las opciones asociadas al perfil
            Query q = em.createNamedQuery("deleteopcionxgrupo");
            q.setParameter("codigoPerfil", perfil.getCodigo());
            q.executeUpdate();

            // Borrar los menus asociados al perfil
            q = em.createNamedQuery("deletemenuxgrupo");
            q.setParameter("codigoPerfil", perfil.getCodigo());
            q.executeUpdate();

            // Insertar los menus
            if (listaMenu != null) {
                Iterator it = listaMenu.iterator();
                while (it.hasNext()) {
                    MenuVO menu = (MenuVO)it.next();
                    q = em.createNamedQuery("insertmenuxgrupo");
                    q.setParameter("codigoMenu", menu.getCodigo());
                    q.setParameter("codigoPerfil", perfil.getCodigo());
                    q.executeUpdate();
                }
            }
            // Insertar las opciones
            if (listaOpciones != null) {
                Iterator it = listaOpciones.iterator();
                while (it.hasNext()) {
                    OpcionVO opcion = (OpcionVO)it.next();
                    q = em.createNamedQuery("insertopcionxgrupo");
                    q.setParameter("codigoMenu",
                                   opcion.getMenuVO().getCodigo());
                    q.setParameter("codigoOpcion", opcion.getCodigo());
                    q.setParameter("codigoPerfil", perfil.getCodigo());
                    q.executeUpdate();
                }
            }
            // em.getTransaction().commit();
        } catch (Exception e) {
            throw new IdeamException(e);
        } finally {
            em.flush();
        }
    }

    public void updateMenu(MenuVO menu) throws IdeamException {
        em.merge(menu);
        em.flush();
    }

    public void updateOpcion(OpcionVO opcion) throws IdeamException {
        em.merge(opcion);
        em.flush();
    }

    public int countUsuariosPerfil(PerfilVO perfil) throws IdeamException {
        Query query =
            em.createNativeQuery("SELECT COUNT(*) FROM SIRH_USUARIOS_GRUPOS " +
                                 "  where usgr_gprs_codigo = ?1");
        query.setParameter(1, perfil.getCodigo());
        Number countResult = (Number)query.getSingleResult();
        int valor = countResult.intValue();
        em.flush();
        return valor;
    }

    public String getPermisosPerfil(PerfilVO perfil) throws IdeamException {
        Session session = (Session)em.getDelegate();
        SessionFactoryImplementor sfi =
            (SessionFactoryImplementor)session.getSessionFactory();
        ConnectionProvider cp = sfi.getConnectionProvider();
        java.sql.Connection connection = null;

        try {
            connection = cp.getConnection();
            SeguridadDAO dao = new SeguridadDAO();
            return dao.getPermisosTexto(perfil, connection);
        } catch (SQLException e) {
            throw new IdeamException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new IdeamException(e);
            }
        }
    }

    public ParametrosEJB getParametrosService() {
        return parametrosService;
    }

    public void setParametrosService(ParametrosEJB parametrosService) {
        this.parametrosService = parametrosService;
    }

    public List<Funcionario> consultarFuncionarios(String codAutoridadAmbiental) throws IdeamException {
        String sql =
            "SELECT UU.USU_TIPODOCUMENTO as codigoTipoIdentificacion , " +
            "UU.USU_NRODOCUMENTO as identificacionPersona, " +
            "UU.USU_LOGIN as aliasUsuario, " +
            "UU.USU_NOMBRES || ' '  ||  UU.USU_APELLIDOS as nombreFuncionario, " +
            "UU.USU_EMAIL as direccionCorreoElectronico " +
            "FROM SIRH_USUARIOS UU , AUTORIDADES AA " +
            "WHERE AA.ID = UU.USU_AUTORIDAD AND AA.SIGLA = ?1";
        Query q = em.createNativeQuery(sql, Funcionario.class);
        q.setParameter(1, codAutoridadAmbiental);
        return q.getResultList();
    }

}
