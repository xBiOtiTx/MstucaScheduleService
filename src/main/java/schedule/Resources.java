package schedule;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {
	 // use @SuppressWarnings to tell IDE to ignore warnings about field not being referenced directly
	 @SuppressWarnings("unused")
	 @Produces
	 @PersistenceContext
	 private EntityManager em;
	
	 @Produces
	 public Logger produceLog(InjectionPoint injectionPoint) {
	 return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	 }
	
	 @Produces
	 @RequestScoped
	 public FacesContext produceFacesContext() {
	 return FacesContext.getCurrentInstance();
	 }
}
