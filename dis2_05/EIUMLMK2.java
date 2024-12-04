package dis2_05;

public class EIUMLMK2 {
    
}

// Bước 1:
// Tính số sản phẩm mỗi người dự kiến sẽ mua mà không quan tâm giá cả

// dfs1 từ 0: số sản phẩm của nút cha sẽ bằng 1+ tổng số sản phẩm của các nút con

// Bước 2:
// Tính số sản phẩm đã mua và bán thực tế theo giá
// DFS2(v, buyprice)



// dfs2(Vertex v, buyprice){
//     v.visited = true;
//     salePrice = buyPrice * 1.1; // có 1 bug ở đây, từ từ mà debug
//     if (buyPrice > v.acceptedPrice){
//         salePrice = Long.MAX_VALUE;
//     }

//     Duyệt qua các con adj
//         Nếu con mua được thì
//         lấy số lượng con mua trừ vào số lượng cha mua
//         dfs adj
// }

