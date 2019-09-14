package mx.com.televisa.landamark.services.jobs;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ProcessMailCron implements Job{
    public ProcessMailCron() {
        super();
    }

    @Override
    public void execute(JobExecutionContext toJobExecutionContext) throws JobExecutionException {
        //System.out.println("Ejecucion de Cron >> ProcesMailCron ["+new Date()+"]");
        JobDataMap                loDataMap = toJobExecutionContext.getJobDetail().getJobDataMap();
        String                    lsIdService = loDataMap.getString("lsIdService");  
        String                    lsUserName = loDataMap.getString("lsUserName");
        String                    lsIdUser = loDataMap.getString("liIdUser");
        String                    lsIdProcess = loDataMap.getString("liIdProcess");
        String                    lsIdRequest = loDataMap.getString("liIdRequest");
        
        // Obtener Parametros de configuracion de notificaciones para el servicio
        // Validar que existan
        // Obtener destintarios
        // Validar que al menos sea uno
        // Validar con expresion regular que sea un correo (esto no, ya debe estar validado)
        // Enviar mail considerando:
        // * Asunto
        // * Cuerpo del mensaje (algun resultset para tabla)
        // * Destinatarios
        // Enviar correo, invocando secman-controller
        //5541084579
        
    }
}
