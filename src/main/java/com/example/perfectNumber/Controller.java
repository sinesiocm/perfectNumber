package com.example.perfectNumber;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/perfectNumber")
public class Controller {
    	@Autowired
    	private UserRepository userRepository;

	
		@GetMapping(value = "/obter")
		public String obter() {
			List<QueryReturn> ret = userRepository.query();
			StringBuilder retorno = new StringBuilder();
			
			retorno.append("=== REGISTROS NO BANCO DE DADOS ===\n");
			
			for(QueryReturn q : ret) {
				retorno.append(q.getId());
				retorno.append("\n");
				retorno.append(q.getLog());
				retorno.append("================================\n");
			}
			
			return retorno.toString();
		}
	
        @PostMapping(value = "/process")
        public String processar  (@RequestBody NumerosRequest request) {
            int[] numeros = request.getNumeros();
            StringBuilder ret = new StringBuilder();
            
            for (int numero : numeros) {
                ret.append("O número " + numero + (isPerfect(numero) == true ? " EH perfeito" : " NAO EH perfeito"));
                ret.append("\n");
            }
            
            userRepository.save(ret.toString());
            
            return ret.toString();
        }
        
        private static boolean isPerfect(int a) { 
            int n = a; 
            int sum = 0; 
            boolean perfect = false; 

            while (n-- >1) {
                if(a %n == 0) {
                    sum = sum + n; 
                }
                
                if (sum == a) { 
                    perfect = true; 
                } else { 
                    perfect = false; 
                } 
            }
            return perfect;
        }        
        

}