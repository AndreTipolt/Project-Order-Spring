package tipolt.andre.spring.dtos;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotEmpty(message = "Fill Products Field")
    private List<String> listProductId;

    @NotNull(message = "Fill User Field")
    private String userId;

    @NotNull(message = "Fill Quantity Fields")
    private Integer quantity;
}