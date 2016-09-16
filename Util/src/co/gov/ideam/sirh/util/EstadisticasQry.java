package co.gov.ideam.sirh.util;

public enum EstadisticasQry {

    BP_X_DEPTO("SELECT COUNT(bp.ID_DEPARTAMENTO) cantidad ,\n" +
        "  dpt.nombre descripcion\n" +
        "FROM FNTT_BUENAS_PRACTICAS bp\n" +
        "JOIN PART_DIVIPOLA dpt\n" +
        "ON (bp.ID_DEPARTAMENTO = dpt.DIVIPOLA_ID)\n" +
        "GROUP BY bp.ID_DEPARTAMENTO,\n" +
        "  dpt.nombre\n" +
        "ORDER BY COUNT(bp.ID_DEPARTAMENTO) DESC"),
    BP_X_ACTOR("SELECT COUNT(bp.IDACTOR) cantidad ,\n" +
        "  dpt.actor descripcion\n" +
        "FROM FNTT_BUENAS_PRACTICAS bp\n" +
        "JOIN FNTT_ACTORES dpt\n" +
        "ON (bp.IDACTOR= dpt.IDACTOR)\n" +
        "GROUP BY bp.IDACTOR,\n" +
        "  dpt.actor\n" +
        "ORDER BY COUNT(bp.IDACTOR) DESC"),

    FREC_PRINCIPIO("SELECT COUNT(bp.IDPRINCIPIO) cantidad ,\n" +
        "  dpt.PRINCIPIO descripcion\n" +
        "FROM FNTT_PRINCIPIOS_PRACTICAS bp\n" +
        "JOIN FNTT_PRINCIPIOS dpt\n" +
        "ON (bp.IDPRINCIPIO= dpt.IDPRINCIPIO)\n" +
        "GROUP BY bp.IDPRINCIPIO,\n" +
        "  dpt.PRINCIPIO\n" +
        "ORDER BY COUNT(bp.IDPRINCIPIO) DESC"),

    BP_X_COSTO("SELECT COUNT(bp.IDCOSTO) cantidad ,\n" +
        "  dpt.COSTO descripcion\n" +
        "FROM FNTT_BUENAS_PRACTICAS bp\n" +
        "JOIN FNTT_COSTOS dpt\n" +
        "ON (bp.IDCOSTO= dpt.IDCOSTO)\n" +
        "GROUP BY bp.IDCOSTO,\n" +
        "  dpt.COSTO\n" +
        "ORDER BY COUNT(bp.IDCOSTO) DESC"),

    FREC_LOGRO("SELECT COUNT(bp.IDLOGRO) cantidad ,\n" +
        "  dpt.LOGRO descripcion\n" +
        "FROM FNTT_LOGROS_PRACTICAS bp\n" +
        "JOIN FNTT_LOGROS dpt\n" +
        "ON (bp.IDLOGRO= dpt.IDLOGRO)\n" +
        "GROUP BY bp.IDLOGRO,\n" +
        "  dpt.LOGRO\n" +
        "ORDER BY COUNT(bp.IDLOGRO) DESC"),

    BP_X_PROYECTO("SELECT COUNT(bp.IDPROYECTO) cantidad ,\n" +
        "  dpt.DESCRIPCION descripcion\n" +
        "FROM FNTT_BUENAS_PRACTICAS bp\n" +
        "JOIN FNTT_PROYECTOS_EDUCATIVOS dpt\n" +
        "ON (bp.IDPROYECTO= dpt.IDPROYECTO)\n" +
        "GROUP BY bp.IDPROYECTO,\n" +
        "  dpt.DESCRIPCION\n" +
        "ORDER BY COUNT(bp.IDPROYECTO) DESC"),

    BP_X_CATEG("SELECT COUNT(bp.IDCATEGORIA) cantidad ,\n" +
        "  dpt.CATEGORIA descripcion\n" +
        "FROM FNTT_BUENAS_PRACTICAS bp\n" +
        "JOIN FNTT_CATEGORIAS dpt\n" +
        "ON (bp.IDCATEGORIA= dpt.IDCATEGORIA)\n" +
        "GROUP BY bp.IDCATEGORIA,\n" +
        "  dpt.CATEGORIA\n" +
        "ORDER BY COUNT(bp.IDCATEGORIA) DESC"),

    FREC_MOTIVACION("SELECT COUNT(bp.IDMOTIVACION) cantidad ,\n" +
        "  dpt.MOTIVACION descripcion\n" +
        "FROM FNTT_MOTIVACIONES_PRACTICAS bp\n" +
        "JOIN FNTT_MOTIVACIONES dpt\n" +
        "ON (bp.IDMOTIVACION= dpt.IDMOTIVACION)\n" +
        "GROUP BY bp.IDMOTIVACION,\n" +
        "  dpt.MOTIVACION\n" +
        "ORDER BY COUNT(bp.IDMOTIVACION) DESC"),
    CF_X_DEPARTAMENTO("SELECT COUNT(cf.ID_DEPARTAMENTO) cantidad ,\n" +
        "  dpt.nombre descripcion\n" +
        "FROM FNTT_CONFLICTO cf\n" +
        "JOIN PART_DIVIPOLA dpt\n" +
        "ON (cf.ID_DEPARTAMENTO = dpt.DIVIPOLA_ID)\n" +
        "GROUP BY cf.ID_DEPARTAMENTO,\n" +
        "  dpt.nombre\n" +
        "ORDER BY COUNT(cf.ID_DEPARTAMENTO) DESC"),
    FREC_TIPO_CONF("SELECT COUNT(t1.ID_TIPO_CONFLICTO) cantidad ,\n" +
        "  t2.VALOR descripcion\n" +
        "FROM FNTT_CONFLICTO_TIPO t1\n" +
        "JOIN PART_LISTAS t2\n" +
        "ON (t1.ID_TIPO_CONFLICTO = t2.id and t2.ID_CATEGORIA = 80)\n" +
        "GROUP BY t1.ID_TIPO_CONFLICTO,\n" +
        "  t2.VALOR\n" +
        "ORDER BY COUNT(t1.ID_TIPO_CONFLICTO) DESC"),
    CF_X_POBL_AFECT("SELECT COUNT(t1.ID_POBLACION_AFECTADA) cantidad ,\n" +
        "  t2.VALOR descripcion\n" +
        "FROM FNTT_CONFLICTO_POBLACION t1\n" +
        "JOIN PART_LISTAS t2\n" +
        "ON (t1.ID_POBLACION_AFECTADA = t2.id and t2.ID_CATEGORIA = 83)\n" +
        "GROUP BY t1.ID_POBLACION_AFECTADA,\n" +
        "  t2.VALOR\n" +
        "ORDER BY COUNT(t1.ID_POBLACION_AFECTADA) DESC"),
    CF_X_ACTORES("SELECT COUNT(t1.ACTOR_CONFLICTO) cantidad ,\n" +
        "  t2.VALOR descripcion\n" +
        "FROM FNTT_CONFLICTO_ACTOR t1\n" +
        "JOIN PART_LISTAS t2\n" +
        "ON (t1.ACTOR_CONFLICTO = t2.id and t2.ID_CATEGORIA = 82)\n" +
        "GROUP BY t1.ACTOR_CONFLICTO,\n" +
        "  t2.VALOR\n" +
        "ORDER BY COUNT(t1.ACTOR_CONFLICTO) DESC"),
    CF_X_GESTION("SELECT COUNT(t1.ID_TIPO_GESTION) cantidad ,\n" +
        "  t2.VALOR descripcion\n" +
        "FROM FNTT_CONFLICTO_GESTION t1\n" +
        "JOIN PART_LISTAS t2\n" +
        "ON (t1.ID_TIPO_GESTION = t2.id and t2.ID_CATEGORIA = 85)\n" +
        "GROUP BY t1.ID_TIPO_GESTION,\n" +
        "  t2.VALOR\n" +
        "ORDER BY COUNT(t1.ID_TIPO_GESTION) DESC"),
    CF_X_SUB_GESTION("SELECT COUNT(t1.ID_SUBTIPO_GESTION) cantidad ,\n" +
        "  t2.VALOR descripcion\n" +
        "FROM FNTT_CONFLICTO_GESTION t1\n" +
        "JOIN PART_LISTAS t2\n" +
        "ON (t1.ID_SUBTIPO_GESTION = t2.id and t2.ID_CATEGORIA IN (86,87,88,89))\n" +
        "GROUP BY t1.ID_SUBTIPO_GESTION,\n" +
        "  t2.VALOR\n" +
        "ORDER BY COUNT(t1.ID_SUBTIPO_GESTION) DESC"),
    CF_X_CUENCA("SELECT COUNT(cf.ID_AREA) cantidad ,\n" +
        "  dpt.VALOR descripcion\n" +
        "FROM sirh_fuente_tramo_v_ cf\n" +
        "JOIN PART_ZONIFIC_LISTAS dpt\n" +
        "ON (cf.ID_AREA = dpt.ID AND dpt.ID_PADRE IS NULL)\n" +
        "GROUP BY cf.ID_AREA,\n" +
        "  dpt.VALOR\n" +
        "ORDER BY COUNT(cf.ID_AREA) DESC"),
    CF_X_SUBZONA("SELECT COUNT(cf.ID_SUBZONA) cantidad ,\n" +
        "  dpt.VALOR descripcion\n" +
        "FROM sirh_fuente_tramo_v_ cf\n" +
        "JOIN PART_ZONIFIC_LISTAS dpt\n" +
        "ON (cf.ID_SUBZONA = dpt.ID)\n" +
        "GROUP BY cf.ID_SUBZONA,\n" +
        "  dpt.VALOR\n" +
        "ORDER BY COUNT(cf.ID_SUBZONA) DESC");

    private final String query;

    private EstadisticasQry(String query) {
        this.query = query;
    }

    public String getQuery() {
        return this.query;
    }
}
