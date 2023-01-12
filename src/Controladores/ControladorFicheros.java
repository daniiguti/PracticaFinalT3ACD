package Controladores;

import Modelos.Examenes;
import Modelos.Matriculas;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ControladorFicheros {
    private Document doc = null;
    private Transformer xformer = null;
    private Source source = null;
    private Result result = null;   
    private ControladorBDD_HIB cbdd = new ControladorBDD_HIB();  
    
    //Métodos necesarios para guardar un fichero XML
    public void guardarArchivoXML(String rutaArchivo){
        //Inicializamos el doc a un arbol dom vacio
        inicializarDoc();

        //Nos creamos la etiqueta raiz <listaVideojuegos>
        Node nodoRaiz = doc.createElement("matriculas");
        //Hay que añadirla a algo, al padre, como es la raiz, la añadimos al doc
        doc.appendChild(nodoRaiz);

        List<Matriculas> matriculas = cbdd.consultarTodasMatriculas();
        //Recorremos el array
        for(Matriculas aux: matriculas){
            //Nodo padre
            Element examen = doc.createElement("Matricula");
            nodoRaiz.appendChild(examen);
           
            //codigoAlumno            
            Node codigoAlumno = doc.createElement("codAlumno");
            codigoAlumno.setTextContent(aux.getId().getCcodalu());           
            examen.appendChild(codigoAlumno);
            
            //nombreAlumno
            Node nomAlumno = doc.createElement("nomAlumno");
            nomAlumno.setTextContent(aux.getAlumnos().getCnomalu());
            examen.appendChild(nomAlumno);
            
            //codCurso
            Node codigoCurso = doc.createElement("codCurso");
            codigoCurso.setTextContent(aux.getId().getCcodcurso());
            examen.appendChild(codigoCurso);
            
            //nombreCurso
            Node nomCurso = doc.createElement("nomCurso");
            nomCurso.setTextContent(aux.getCursos().getCnomcurso());
            examen.appendChild(nomCurso);
            
            //nota
            Node nota = doc.createElement("notaMedia");
            nota.setTextContent(aux.getNnotamedia() + "");
            examen.appendChild(nota);
        }
        
        //Inicializamos el transformer
        try {
            xformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();    
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace();  
        }
        
        //Propiedades del fichero XML de salida
        xformer.setOutputProperty(OutputKeys.METHOD, "xml");
        xformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
        
        //Definimos la Entrada y la Salida de la Transformacion
        source = new DOMSource(doc); //el doc donde tenemos el arraylist transformado en dom
        result = new StreamResult(new File(rutaArchivo)); //donde vamos a guardar el dom
 
        //Realizamos la Transformación mediante el metodo transform()
        try {
            xformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }   
    private void inicializarDoc() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.newDocument();
        } catch (ParserConfigurationException ex) {            
        }       
    }
    
    public void guardarArchivoJSON(String rutaArchivo, String codAlumno, String codCurso){
        FileWriter fw = null;
        BufferedWriter bw = null;
        String lineaEscribir = "";
        try {              
            fw = new FileWriter(new File(rutaArchivo));
            bw = new BufferedWriter(fw);
            
            List<Examenes> examenes = cbdd.consultarTodosExamanes(codCurso, codAlumno);
            bw.write("{\"examenes\":[" + "\n");
            for(int i= 0; i < examenes.size(); i++){                   
               
                //Para que cuando vaya por el ultimo no dibuje la ultima coma
                if(i == examenes.size() - 1){
                    lineaEscribir = examenes.get(i).toString() + "\n"; 
                }else{
                    lineaEscribir = examenes.get(i).toString() + ", \n"; 
                }
                bw.write(lineaEscribir);
            }
            bw.write("]}");
        } catch (IOException ex) {
            System.out.println("Archivo no existe");
        }finally{
            try {
                bw.close();
                fw.close();
            } catch (IOException ex) {                
            }                
        }
    }
}
