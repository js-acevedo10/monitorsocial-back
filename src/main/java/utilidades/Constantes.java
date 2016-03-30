package utilidades;

public class Constantes {
	
	//------------------------------------------------------------------------------------
	//---------------------------------------TWITTER--------------------------------------
	//------------------------------------------------------------------------------------
	
	//CONSTANTES PARA EL TIPO DE TWEET EN TÉRMINOS DE SU CATEGORÍA CRM
	public static final int TWEET_TIPO_SOPORTE = 1;
	public static final int TWEET_TIPO_QUEJA = 2;
	public static final int TWEET_TIPO_PETICION = 3;
	public static final int TWEET_TIPO_RECLAMO = 4;
	public static final int TWEET_TIPO_OTROS = 5;
	
	//------------------------------------------------------------------------------------
	//---------------------------------------INCIDENCIAS----------------------------------
	//------------------------------------------------------------------------------------
	
	//CONSTANTES PARA LA ETAPA DE UN CASO INDEPENDIENTEMENTE DE SU CATEGORÍA CRM (CUALQUIERA EXCEPTO OTROS)
	public static final int CASO_ETAPA_REGISTRADA = 0;
	public static final int CASO_ETAPA_COLA = 1;
	public static final int CASO_ETAPA_INVESTIGANDO = 2;
	public static final int CASO_ETAPA_ESPERANDO = 3;
	public static final int CASO_ETAPA_RESUELTA = 4;
	public static final int CASO_ETAPA_CONFIRMADA = 5;
	
	//CONSTANTES PARA EL ORIGEN DE UN CASO O INCIDENCIA
	public static final int CASO_ORIGEN_TELEFONO = 1;
	public static final int CASO_ORIGEN_CORREO = 2;
	public static final int CASO_ORIGEN_FAX = 3;
	public static final int CASO_ORIGEN_WEB = 4;
	public static final int CASO_ORIGEN_CARTA = 5;
	public static final int CASO_ORIGEN_EMPLEADO = 6;
	public static final int CASO_ORIGEN_TWITTER = 7;
	
	//CONSTANTES PARA EL ESTADO DE UN CASO
	public static final int CASO_ESTADO_PASADO = 1;
	public static final int CASO_ESTADO_PRESENTE = 2;
	public static final int CASO_ESTADO_FUTURO = 3;
	
	//------------------------------------------------------------------------------------
	//---------------------------------------EMPRESAS-------------------------------------
	//------------------------------------------------------------------------------------
	
	//CONSTANTES PARA LOS TIPOS DE EMPRESA
	public static final int EMPRESA_TIPO_CLIENTE = 1;
	public static final int EMPRESA_TIPO_CLIENTE_POTENCIAL = 2;
	public static final int EMPRESA_TIPO_COMPETENCIA = 3;
	public static final int EMPRESA_TIPO_ANALISTA = 4;
	public static final int EMPRESA_TIPO_SOCIO = 5;
	public static final int EMPRESA_TIPO_DISTRIBUIDOR = 6;
	public static final int EMPRESA_TIPO_PROVEEDOR = 7;
	
	//CONSTANTES PARA EL ESTADO DE UNA EMPRESA
	public static final int EMPRESA_ESTADO_ACTIVA = 1;
	public static final int EMPRESA_ESTADO_CERRADA = 2;
	public static final int EMPRESA_ESTADO_INACTIVA = 3;
	
	//ORIGEN DE RELACION CON EMPRESA
	public static final int EMPRESA_ORIGEN_TELEFONO = 1;
	public static final int EMPRESA_ORIGEN_CORREO = 2;
	public static final int EMPRESA_ORIGEN_FAX = 3;
	public static final int EMPRESA_ORIGEN_WEB = 4;
	public static final int EMPRESA_ORIGEN_CARTA = 5;
	public static final int EMPRESA_ORIGEN_EMPLEADO = 6;
	public static final int EMPRESA_ORIGEN_CLIENTE = 7;
	public static final int EMPRESA_ORIGEN_FERIA = 8;
	public static final int EMPRESA_ORIGEN_PRENSA = 9;
	
	//------------------------------------------------------------------------------------
	//---------------------------------------GENERAL--------------------------------------
	//------------------------------------------------------------------------------------
	
	//CONSTANTES PARA EL ESTADO DE PRIORIDAD DE CUALQUIER COSA
	public static final int PRIORIDAD_ALTA = 1;
	public static final int PRIORIDAD_MEDIA = 2;
	public static final int PRIORIDAD_BAJA = 3;
}