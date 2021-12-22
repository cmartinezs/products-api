package io.cmartinez.productsapi.app.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author Carlos
 * @version 1.0
 */

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

    private static final String URL_REGEX = "((http|https)://)(www.)?[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";

    @NotBlank
    @Size(min = 12, max = 13)
    private String sku;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    @Size(min = 2, max = 50)
    private String brand;

    @Size(min = 1)
    private String size;

    @DecimalMin("1.00")
    @DecimalMax("99999999.00")
    private double price;


    @NotBlank
    @Pattern(regexp = URL_REGEX)
    private String principalImage;

    private List<@NotBlank @Pattern(regexp = URL_REGEX) String> otherImages;
}
