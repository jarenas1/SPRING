package interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
public class CalendarInterceptor implements HandlerInterceptor {

    //INYECTAMOS ARCHIVOS DEL PROPERTIES

@Value("${config.calendar.open}")
private Integer open;
@Value(("${config.calendar.close}"))
private Integer close;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance(); //TRAEMOS LA HORA
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (open <= hour && close>hour){
            String message = "hola hola hola sapito";
            //devolvemos a la request el mensaje
            request.setAttribute("message", message);
            return true; //ESTA DATA DEBE SER RECIBIDA EN EL CONTROLLADOR POR MEDIO DE LA REQUEST
        }
        //Logica si la request no es exitosa
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> data = new HashMap<>();
        data.put("Message", "cerrado, fuera de horario");
        data.put("vsitenos desde las", open);

        //pasamos a json para que pueda ser devuelt
        response.setContentType("applicstion/json");
        response.setStatus(401);
        response.getWriter().write(mapper.writeValueAsString(data));
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
