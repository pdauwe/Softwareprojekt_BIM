package awk.factory;

import awk.usecase.IStundenplanErstellen;
import awk.usecase.IZeitpraeferenzErfassen;

public interface IStundenplanFactory {

	public IStundenplanErstellen getStundenplanErstellen();
	public IZeitpraeferenzErfassen getZeitpraeferenzenErfassen();
}
