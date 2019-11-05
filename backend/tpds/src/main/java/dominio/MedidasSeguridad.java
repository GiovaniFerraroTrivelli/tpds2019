package dominio;

public class MedidasSeguridad {
	private Boolean seGuardaEnGarage;
	private Boolean poseeAlarma;
	private Boolean poseeRastreoVehicular;
	private Boolean poseeTuercasAntirrobo;

	public MedidasSeguridad() {

	}

	public Boolean getSeGuardaEnGarage() {
		return seGuardaEnGarage;
	}

	public void setSeGuardaEnGarage(Boolean seGuardaEnGarage) {
		this.seGuardaEnGarage = seGuardaEnGarage;
	}

	public Boolean getPoseeAlarma() {
		return poseeAlarma;
	}

	public void setPoseeAlarma(Boolean poseeAlarma) {
		this.poseeAlarma = poseeAlarma;
	}

	public Boolean getPoseeRastreoVehicular() {
		return poseeRastreoVehicular;
	}

	public void setPoseeRastreoVehicular(Boolean poseeRastreoVehicular) {
		this.poseeRastreoVehicular = poseeRastreoVehicular;
	}

	public Boolean getPoseeTuercasAntirrobo() {
		return poseeTuercasAntirrobo;
	}

	public void setPoseeTuercasAntirrobo(Boolean poseeTuercasAntirrobo) {
		this.poseeTuercasAntirrobo = poseeTuercasAntirrobo;
	}

}
