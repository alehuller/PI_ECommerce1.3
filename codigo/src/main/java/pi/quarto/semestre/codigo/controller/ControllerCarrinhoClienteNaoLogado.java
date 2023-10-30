package pi.quarto.semestre.codigo.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pi.quarto.semestre.codigo.dao.CarrinhoDAO;
import pi.quarto.semestre.codigo.model.CarrinhoAllDto;

@Controller
public class ControllerCarrinhoClienteNaoLogado {

    @GetMapping("carrinhoClienteNaoLogado")
    public String carrinhoClienteNaoLogado(Model model) throws SQLException {
        CarrinhoDAO carrinhoRepository = new CarrinhoDAO();
        List<CarrinhoAllDto> carrinhos = carrinhoRepository.findCarrinhos();
        model.addAttribute("carrinhos", carrinhos);

        for (CarrinhoAllDto carrinho : carrinhos) {
            System.out.println("==============");
            System.out.println("Carrinho");
            System.out.println("Nome do Produto: " + carrinho.getNome());
            System.out.println("Quantidade: " + carrinho.getQuantidade());
            System.out.println();
        }
        return "/carrinhoClienteNaoLogado";
    }
}
