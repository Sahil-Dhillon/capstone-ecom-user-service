package com.ecom.dto.cart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
 
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistDto {
    private int wishlistId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<WishlistItemDto> items;
}