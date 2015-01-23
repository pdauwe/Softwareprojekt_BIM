package awk.factory.impl;

import awk.factory.IStundenplanFactory;
import awk.usecase.IStundenplanErstellen;
import awk.usecase.IZeitpraeferenzErfassen;
import awk.usecase.impl.StundenplanErstellen;
import awk.usecase.impl.ZeitpraeferenzErfassen;

public class StundenplanFactory implements IStundenplanFactory {

	@Override
	public IStundenplanErstellen getStundenplanErstellen() {
		return new StundenplanErstellen();
	}

	@Override
	public IZeitpraeferenzErfassen getZeitpraeferenzenErfassen() {
		return new ZeitpraeferenzErfassen();
	}

}
