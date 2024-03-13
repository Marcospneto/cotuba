package cotuba.application;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import cotuba.domain.Capitulo;
import cotuba.domain.Ebook;
import cotuba.epub.GeradorEPUB;
import cotuba.pdf.GeradorPDF;

public class Cotuba {
	/*Lista de geradores ebook*/
	private final List<GeradorEbook> geradores;
	
	/*Inicializa a lista de geradores do ebook com os geradores padrão*/
	public Cotuba() {
	this.geradores = new ArrayList<>();
	this.geradores.add(new GeradorPDF());
	this.geradores.add(new GeradorEPUB());
		
		}
	/*Metodo para gerar ebook*/
    public void executa(String formato, Path diretorioDosMD, Path arquivoDeSaida){
        /*Renderiza os capitulos do diretorio de markdown para html*/
    	RenderizadorMDParaHTML renderizador = RenderizadorMDParaHTML.cria();
        List<Capitulo> capitulos = renderizador.renderiza(diretorioDosMD);
        
        /*Cria o objeto ebook com os dados necessarios*/
        Ebook ebook = new Ebook();
        ebook.setFormato(formato);
        ebook.setArquivoDeSaida(arquivoDeSaida);
        ebook.setCapitulos(capitulos);
     
        // Itera sobre os geradores de ebook na lista de geradpres (cadeia de responsabilidade)
        for (GeradorEbook gerador : geradores) {
        	/*Verifica se o gerador pode lidar com o formato especificado*/
        	if (gerador.aceitaFormato(formato)) {
        		/*Gera o ebook usando o gerador atual*/
        		gerador.gera(ebook);
        		return; // apos gerar o ebook encerra a execução
        	}
        	
         }
        /*Se nenhum gerador puder lidar com o formato especificado, lança uma exceção*/
        throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
       
        
        
        /*if ("pdf".equals(formato)) {
            gerador = new GeradorPDF();
        } else if ("epub".equals(formato)) {
            gerador = new GeradorEPUB();
        } else {
            throw new IllegalArgumentException("Formato do ebook inválido: " + formato);
        }

        gerador.gera(ebook);*/
    }
}
