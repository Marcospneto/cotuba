package cotuba.application;

import cotuba.domain.Ebook;

public interface GeradorEbook {
    /*Método para gerar o Ebook*/
	public void gera(Ebook ebook);
	/*Método para verificar se o gerador pode lidar com o formato especificado*/
    public boolean aceitaFormato(String formato);

}


