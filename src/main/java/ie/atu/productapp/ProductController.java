package ie.atu.productapp;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private List<Product> productList = new ArrayList<>();
    public ProductController(){
        productList.add (new Product("100", "Tv", "Electric", 399));
        productList.add (new Product("101", "Radio", "Electric", 99));
    }

    @GetMapping("/getProducts")
    public List<Product> getProduct()
    {
        return productList;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<List> addProduct(@RequestBody Product product)
    {
        productList.add(product);
        return ResponseEntity.ok(productList);
    }
    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        for (Product product : productList) { // runs through each entry into array
            if (product.getId().equals(id)) {   //checks id
                product.setName(updatedProduct.getName());  //updates each detail of product
                product.setCategory(updatedProduct.getCategory());
                product.setPrice(updatedProduct.getPrice());
                return ResponseEntity.ok("Product updated successfully.");
            }
        }
        return ResponseEntity.ok("Product not found");
    }
}
