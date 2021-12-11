package lapr.project.data.api;

import lapr.project.utils.Constants;
import lapr.project.utils.*;

import java.util.List;

public class ProximityMap {
    private AdjacencyMatrixGraph map;
    private boolean isDrone;
    private final int numPharmacies = 9;

    public ProximityMap(boolean isDrone){
        this.map = new AdjacencyMatrixGraph();
        this.isDrone = isDrone;
        setUp();
    }

    @Override
    public String toString() {
        return "ProximityMap{" +
                "map=" + map +
                '}';
    }
    /*
    private boolean setUp(){
        //Vertex insertion
        Pair<Double, Double> coordinates = Pair.createPair(ph.getAddress().getCoordinateX(), ph.getAddress().getCoordinateY());
        Vertex v0 = new Vertex(map.numVertices(), coordinates, ph.getAddress().getElevation());
        map.insertVertex(v0);
        Pair<Double, Double > p1 = Pair.createPair(41.159366, -8.630432);
        Vertex v1 = new Vertex(map.numVertices(), p1, 86);
        map.insertVertex(v1);
        Pair<Double, Double > p2 = Pair.createPair(41.158111, -8.632239);
        Vertex v2 = new Vertex(map.numVertices(), p2, 87);
        map.insertVertex(v2);
        Pair<Double, Double > p3 = Pair.createPair(41.161366, -8.631855);
        Vertex v3 = new Vertex(map.numVertices(), p3, 87);
        map.insertVertex(v3);
        Pair<Double, Double > p4 = Pair.createPair(41.160700, -8.636239);
        Vertex v4 = new Vertex(map.numVertices(), p4, 86);
        map.insertVertex(v4);
        Pair<Double, Double > p5 = Pair.createPair(41.156700, -8.641239);
        Vertex v5 = new Vertex(map.numVertices(), p5, 87);
        map.insertVertex(v5);
        Pair<Double, Double > p6 = Pair.createPair(41.154700, -8.644239);
        Vertex v6= new Vertex(map.numVertices(), p6, 88);
        map.insertVertex(v6);
        Pair<Double, Double > p7 = Pair.createPair(41.154700, -8.645239);
        Vertex v7 = new Vertex(map.numVertices(), p7, 88);
        map.insertVertex(v7);
        Pair<Double, Double > p8 = Pair.createPair(41.153700, -8.644239);
        Vertex v8 = new Vertex(map.numVertices(), p8, 89);
        map.insertVertex(v8);
        Pair<Double, Double > p9 = Pair.createPair(41.154785, -8.626800);
        Vertex v9 = new Vertex(map.numVertices(), p9, 87);
        map.insertVertex(v9);
        Pair<Double, Double > p10 = Pair.createPair(41.158052, -8.625600);
        Vertex v10 = new Vertex(map.numVertices(), p10, 85);
        map.insertVertex(v10);
        Pair<Double, Double > p11 = Pair.createPair(41.150300, -8.629100);
        Vertex v11 = new Vertex(map.numVertices(), p11, 87);
        map.insertVertex(v11);
        Pair<Double, Double > p12 = Pair.createPair(41.159600, -8.628600);
        Vertex v12 = new Vertex(map.numVertices(), p12, 85);
        map.insertVertex(v12);
        Pair<Double, Double > p13 = Pair.createPair(41.160700, -8.629800);
        Vertex v13 = new Vertex(map.numVertices(), p13, 84);
        map.insertVertex(v13);
        Pair<Double, Double > p14 = Pair.createPair(41.161145, -8.628600);
        Vertex v14 = new Vertex(map.numVertices(), p14, 84);
        map.insertVertex(v14);
        Pair<Double, Double > p15 = Pair.createPair(41.160700, -8.626800);
        Vertex v15 = new Vertex(map.numVertices(), p15, 83);
        map.insertVertex(v15);
        Pair<Double, Double > p16 = Pair.createPair(41.157455, -8.621600);
        Vertex v16 = new Vertex(map.numVertices(), p16, 83);
        map.insertVertex(v16);
        Pair<Double, Double > p17 = Pair.createPair(41.156455, -8.623600);
        Vertex v17 = new Vertex(map.numVertices(), p17, 85);
        map.insertVertex(v17);
        Pair<Double, Double > p18 = Pair.createPair(41.155489, -8.624800);
        Vertex v18 = new Vertex(map.numVertices(), p18, 86);
        map.insertVertex(v18);
        Pair<Double, Double > p19 = Pair.createPair(41.154700, -8.638500);
        Vertex v19 = new Vertex(map.numVertices(), p19, 90);
        map.insertVertex(v19);
        Pair<Double, Double > p20 = Pair.createPair(41.153200, -8.641239);
        Vertex v20 = new Vertex(map.numVertices(), p20, 90);
        map.insertVertex(v20);
        Pair<Double, Double > p21 = Pair.createPair(41.152700, -8.638239);
        Vertex v21 = new Vertex(map.numVertices(), p21, 90);
        map.insertVertex(v21);
        Pair<Double, Double > p22 = Pair.createPair(41.152700, -8.636239);
        Vertex v22 = new Vertex(map.numVertices(), p22, 90);
        map.insertVertex(v22);
        Pair<Double, Double > p23 = Pair.createPair(41.154700, -8.635500);
        Vertex v23 = new Vertex(map.numVertices(), p23, 90);
        map.insertVertex(v23);
        Pair<Double, Double > p24 = Pair.createPair(41.154000, -8.633500);
        Vertex v24 = new Vertex(map.numVertices(), p24, 89);
        map.insertVertex(v24);
        Pair<Double, Double > p25 = Pair.createPair(41.150300, -8.627100);
        Vertex v25 = new Vertex(map.numVertices(), p25, 88);
        map.insertVertex(v25);
        Pair<Double, Double > p26 = Pair.createPair(41.153000, -8.632500);
        Vertex v26 = new Vertex(map.numVertices(), p26, 88);
        map.insertVertex(v26);
        Pair<Double, Double > p27 = Pair.createPair(41.152500, -8.631100);
        Vertex v27 = new Vertex(map.numVertices(), p27, 87);
        map.insertVertex(v27);
        Pair<Double, Double > p28 = Pair.createPair(41.152700, -8.628500);
        Vertex v28 = new Vertex(map.numVertices(), p28, 87);
        map.insertVertex(v28);
        Pair<Double, Double > p29 = Pair.createPair(41.151300, -8.628500);
        Vertex v29 = new Vertex(map.numVertices(), p29, 87);
        map.insertVertex(v29);

        //Edge creation
        Edge e01 = new Edge("Rua a", GraphOperations.calculateDistance(v0.getCoordinates(), v1.getCoordinates()), v0, v1, isDrone, 180);
        Edge e010 = new Edge("Rua b", GraphOperations.calculateDistance(v0.getCoordinates(), v10.getCoordinates()), v0, v10, isDrone, 0);
        Edge e019 = new Edge("Rua c", GraphOperations.calculateDistance(v0.getCoordinates(), v19.getCoordinates()), v0, v19, isDrone, 90);

        Edge e10 = new Edge("Rua a", GraphOperations.calculateDistance(v0.getCoordinates(), v1.getCoordinates()), v1, v0, isDrone, 180);
        Edge e12 = new Edge("Rua d", GraphOperations.calculateDistance(v1.getCoordinates(),v2.getCoordinates()), v1, v2, isDrone, 0);
        Edge e13 = new Edge("Rua e", GraphOperations.calculateDistance(v1.getCoordinates(), v3.getCoordinates()), v1, v3, isDrone, 90);
        Edge e112 = new Edge ("Rua f", GraphOperations.calculateDistance(v1.getCoordinates(), v12.getCoordinates()), v1, v12, isDrone, 180);

        Edge e21 = new Edge("Rua d", GraphOperations.calculateDistance(v2.getCoordinates(), v1.getCoordinates()), v2, v1, isDrone, 180);
        Edge e24 = new Edge("Rua g", GraphOperations.calculateDistance(v2.getCoordinates(), v4.getCoordinates()), v2, v4, isDrone, 0);
        Edge e25 = new Edge("Rua h", GraphOperations.calculateDistance(v2.getCoordinates(), v5.getCoordinates()), v2, v5, isDrone, 90);
        Edge e219 = new Edge("Rua i", GraphOperations.calculateDistance(v2.getCoordinates(), v19.getCoordinates()), v2, v19, isDrone, 180);

        Edge e34 = new Edge("Rua j", GraphOperations.calculateDistance(v3.getCoordinates(), v4.getCoordinates()), v3, v4, isDrone, 180);

        Edge e42 = new Edge("Rua g", GraphOperations.calculateDistance(v4.getCoordinates(), v2.getCoordinates()), v4, v2, isDrone, 0);

        Edge e54 = new Edge("Rua k", GraphOperations.calculateDistance(v5.getCoordinates(), v4.getCoordinates()), v5, v4, isDrone, 90);
        Edge e56 = new Edge("Rua l", GraphOperations.calculateDistance(v5.getCoordinates(), v6.getCoordinates()), v5, v6, isDrone, 180);

        Edge e67 = new Edge("Rua m", GraphOperations.calculateDistance(v6.getCoordinates(), v7.getCoordinates()), v6, v7, isDrone, 0);
        Edge e68 = new Edge("Rua n", GraphOperations.calculateDistance(v6.getCoordinates(), v8.getCoordinates()), v6, v8, isDrone, 90);

        Edge e76 = new Edge("Rua m", GraphOperations.calculateDistance(v7.getCoordinates(), v6.getCoordinates()), v7, v6, isDrone, 180);

        Edge e819 = new Edge("Rua o", GraphOperations.calculateDistance(v8.getCoordinates(), v19.getCoordinates()), v8, v19, isDrone, 0);
        Edge e820 = new Edge("Rua p", GraphOperations.calculateDistance(v8.getCoordinates(), v20.getCoordinates()), v8, v20, isDrone, 90);

        Edge e90 = new Edge("Rua q", GraphOperations.calculateDistance(v9.getCoordinates(), v0.getCoordinates()), v9, v0, isDrone, 180);

        Edge e100 = new Edge("Rua b", GraphOperations.calculateDistance(v10.getCoordinates(), v0.getCoordinates()), v10, v0, isDrone, 0);
        Edge e1018 = new Edge("Rua r", GraphOperations.calculateDistance(v10.getCoordinates(), v18.getCoordinates()), v10, v18, isDrone, 90);

        Edge e119 = new Edge("Rua s", GraphOperations.calculateDistance(v11.getCoordinates(), v9.getCoordinates()), v11, v9, isDrone, 0);

        Edge e1213 = new Edge("Rua t", GraphOperations.calculateDistance(v12.getCoordinates(), v13.getCoordinates()), v12, v13, isDrone, 180);
        Edge e1210 = new Edge("Rua u", GraphOperations.calculateDistance(v12.getCoordinates(), v10.getCoordinates()), v12, v10, isDrone, 90);
        Edge e1216 = new Edge("Rua z", GraphOperations.calculateDistance(v12.getCoordinates(), v16.getCoordinates()), v12, v16, isDrone, 0);

        Edge e133 = new Edge("Rua v", GraphOperations.calculateDistance(v13.getCoordinates(), v3.getCoordinates()), v13, v3, isDrone, 180);
        Edge e1314 = new Edge("Rua w", GraphOperations.calculateDistance(v13.getCoordinates(), v14.getCoordinates()), v13, v14, isDrone, 0);

        Edge e1413 = new Edge("Rua w", GraphOperations.calculateDistance(v14.getCoordinates(), v13.getCoordinates()), v14, v13, isDrone, 90);
        Edge e1415 = new Edge("Rua x", GraphOperations.calculateDistance(v14.getCoordinates(), v15.getCoordinates()), v14, v15, isDrone, 180);

        Edge e1514 = new Edge("Rua x", GraphOperations.calculateDistance(v15.getCoordinates(), v14.getCoordinates()), v15, v14, isDrone, 0);
        Edge e1512 = new Edge("Rua z", GraphOperations.calculateDistance(v15.getCoordinates(), v14.getCoordinates()), v15, v14, isDrone, 90);

        Edge e1617 = new Edge("Rua aa", GraphOperations.calculateDistance(v16.getCoordinates(), v17.getCoordinates()), v16, v17, isDrone, 180);

        Edge e1718 = new Edge("Rua ab", GraphOperations.calculateDistance(v17.getCoordinates(), v18.getCoordinates()), v17, v18, isDrone, 0);

        Edge e189 = new Edge("Rua ac", GraphOperations.calculateDistance(v18.getCoordinates(), v19.getCoordinates()), v18, v19, isDrone, 90);

        Edge e192 = new Edge("Rua i", GraphOperations.calculateDistance(v19.getCoordinates(), v2.getCoordinates()), v19, v2, isDrone, 180);
        Edge e190 = new Edge("Rua c", GraphOperations.calculateDistance(v19.getCoordinates(), v0.getCoordinates()), v19, v0, isDrone, 0);
        Edge e1923 = new Edge("Rua ad", GraphOperations.calculateDistance(v19.getCoordinates(), v23.getCoordinates()), v19, v23, isDrone, 90);

        Edge e208 = new Edge("Rua p", GraphOperations.calculateDistance(v20.getCoordinates(), v8.getCoordinates()), v20, v8, isDrone, 180);
        Edge e2021 = new Edge("Rua ae", GraphOperations.calculateDistance(v20.getCoordinates(), v21.getCoordinates()), v20, v21, isDrone, 90);

        Edge e2119 = new Edge("Rua af", GraphOperations.calculateDistance(v21.getCoordinates(), v19.getCoordinates()), v21, v19, isDrone, 0);

        Edge e2221 = new Edge("Rua ag", GraphOperations.calculateDistance(v22.getCoordinates(), v21.getCoordinates()), v22, v21, isDrone, 180);
        Edge e2223 = new Edge("Rua ah", GraphOperations.calculateDistance(v22.getCoordinates(), v23.getCoordinates()), v22, v23, isDrone, 90);

        Edge e2322 = new Edge("Rua ah", GraphOperations.calculateDistance(v23.getCoordinates(), v22.getCoordinates()), v23, v22, isDrone, 0);
        Edge e2324 = new Edge("Rua ai", GraphOperations.calculateDistance(v23.getCoordinates(), v24.getCoordinates()), v23, v24, isDrone, 180);

        Edge e2425 = new Edge("Rua aj", GraphOperations.calculateDistance(v24.getCoordinates(), v25.getCoordinates()), v24, v25, isDrone, 90);
        Edge e2426 = new Edge("Rua ak", GraphOperations.calculateDistance(v24.getCoordinates(), v26.getCoordinates()), v24, v26, isDrone, 0);

        Edge e2511 = new Edge("Rua am", GraphOperations.calculateDistance(v25.getCoordinates(), v11.getCoordinates()), v25, v11, isDrone, 180);
        Edge e2529 = new Edge("Rua an", GraphOperations.calculateDistance(v25.getCoordinates(), v29.getCoordinates()), v25, v29, isDrone, 0);

        Edge e2627 = new Edge("Rua al", GraphOperations.calculateDistance(v26.getCoordinates(), v27.getCoordinates()), v26, v27, isDrone, 90);

        Edge e2728 = new Edge("Rua ap", GraphOperations.calculateDistance(v27.getCoordinates(), v28.getCoordinates()), v27, v28, isDrone, 180);
        Edge e2729 = new Edge("Rua ao", GraphOperations.calculateDistance(v27.getCoordinates(), v29.getCoordinates()), v27, v29, isDrone, 0);

        Edge e289 = new Edge("Rua aq", GraphOperations.calculateDistance(v28.getCoordinates(), v9.getCoordinates()), v28, v9, isDrone, 90);

        Edge e2927 = new Edge("Rua ao", GraphOperations.calculateDistance(v29.getCoordinates(), v27.getCoordinates()), v29, v27, isDrone, 180);
        Edge e2911 = new Edge("Rua ar", GraphOperations.calculateDistance(v29.getCoordinates(), v11.getCoordinates()), v29, v11, isDrone, 0);

        //Edge insertion
        insertEdges(v0, v1, e01);
        insertEdges(v0, v10, e010);
        insertEdges(v0, v19, e019);
        insertEdges(v1, v0, e10);
        insertEdges(v1, v2, e12);
        insertEdges(v1, v3, e13);
        insertEdges(v1, v12, e112);
        insertEdges(v2, v1, e21);
        insertEdges(v2, v4, e24);
        insertEdges(v2, v5, e25);
        //insertEdges(v2, v19, e219);
        insertEdges(v3, v4, e34);
        insertEdges(v4, v2, e42);
        insertEdges(v5, v4, e54);
        insertEdges(v5, v6, e56);
        insertEdges(v6, v7, e67);
        insertEdges(v6, v8, e68);
        insertEdges(v7, v6, e76);
        insertEdges(v8, v19, e819);
        insertEdges(v8, v20, e820);
        insertEdges(v9, v0, e90);
        insertEdges(v10, v0, e100);
        insertEdges(v10, v18, e1018);
        insertEdges(v11, v9, e119);
        insertEdges(v12, v13, e1213);
        insertEdges(v12, v10, e1210);
        insertEdges(v12, v16, e1216);
        insertEdges(v13, v3, e133);
        insertEdges(v13, v14, e1314);
        insertEdges(v14, v13, e1413);
        insertEdges(v14, v15, e1415);
        insertEdges(v15, v14, e1514);
        insertEdges(v15, v12, e1512);
        insertEdges(v16, v17, e1617);
        insertEdges(v17, v18, e1718);
        insertEdges(v18, v9, e189);
        insertEdges(v19, v2, e192);
        insertEdges(v19, v0, e190);
        insertEdges(v19, v23, e1923);
        insertEdges(v20, v8, e208);
        insertEdges(v20, v21, e2021);
        insertEdges(v21, v19, e2119);
        insertEdges(v21, v19, e2119);
        insertEdges(v22, v21, e2221);
        insertEdges(v22, v23, e2223);
        insertEdges(v23, v22, e2322);
        insertEdges(v23, v24, e2324);
        insertEdges(v24, v25, e2425);
        insertEdges(v24, v26, e2426);
        insertEdges(v25, v11, e2529);
        insertEdges(v26, v27, e2627);
        insertEdges(v27, v28, e2728);
        insertEdges(v27, v29, e2729);
        insertEdges(v28, v9, e289);
        insertEdges(v29, v27, e2927);
        insertEdges(v29, v11, e2911);
        insertEdges(v13, v14, e1314);
        insertEdges(v25, v11, e2511);
        return true;
    }

     */

    private void setUp(){
        //pharmacy insertion
        Pair<Double, Double> p0 = Pair.createPair(41.20019, -8.66614);
        Vertex v0 = new Vertex(map.numVertices(), p0, 74); //Farmácia Guifões
        v0.setName("Farmacia Guifoes");
        map.insertVertex(v0);
        Pair<Double, Double> p1 = Pair.createPair(41.16064, -8.64377);
        Vertex v1 = new Vertex(map.numVertices(), p1, 71); //Farmacia Avenida
        v1.setName("Farmacia Avenida");
        map.insertVertex(v1);
        Pair<Double, Double> p2 = Pair.createPair(41.12624, -8.60603);
        Vertex v2 = new Vertex(map.numVertices(), p2, 106); //Farmácia Couto
        v2.setName("Farmacia Couto");
        map.insertVertex(v2);
        Pair<Double, Double> p3 = Pair.createPair(41.14855, -8.60799);
        Vertex v3 = new Vertex(map.numVertices(), p3, 87); //Farmácia Sá da Bandeira
        v3.setName("Farmacia Sa da Bandeira");
        map.insertVertex(v3);
        Pair<Double, Double> p4 = Pair.createPair(41.16722, -8.61182);
        Vertex v4 = new Vertex(map.numVertices(), p4, 128); //Farmácia sá
        v4.setName("Farmacia Sa");
        map.insertVertex(v4);
        Pair<Double, Double> p5 = Pair.createPair(41.21990, -8.56090);
        Vertex v5 = new Vertex(map.numVertices(), p5, 83); //Farmácia Sousa Torres
        v5.setName("Farmacia Sousa Torres");
        map.insertVertex(v5);
        Pair<Double, Double> p6 = Pair.createPair(41.16131, -8.53143);
        Vertex v6 = new Vertex(map.numVertices(), p6, 91); //Farmácia de Fânzeres
        v6.setName("Farmacia de Fânzeres");
        map.insertVertex(v6);
        Pair<Double, Double> p7 = Pair.createPair(41.19784, -8.49954);
        Vertex v7 = new Vertex(map.numVertices(), p7, 152); //Farmácia Marques da Cunha
        v7.setName("Farmacia Marques da Cunha");
        map.insertVertex(v7);
        Pair<Double, Double> p8 = Pair.createPair(41.19012, -8.49786);
        Vertex v8 = new Vertex(map.numVertices(), p8, 143); //Farmácia Central (Valongo)
        v8.setName("Farmácia Central (Valongo)");
        map.insertVertex(v8);

        //client insertion
        Pair<Double, Double> p9 = Pair.createPair(41.23359, -8.62167);
        Vertex v9 = new Vertex(map.numVertices(), p9, 107); //Centro Comercial Plaza
        v9.setName("Centro Comercial Plaza");
        map.insertVertex(v9);
        Pair<Double, Double> p10 = Pair.createPair(41.14625, -8.59509);
        Vertex v10 = new Vertex(map.numVertices(), p10, 98); //Centro Comercial Stop
        v10.setName("Centro Comercial Stop");
        map.insertVertex(v10);
        Pair<Double, Double> p11 = Pair.createPair(41.15471, -8.61918);
        Vertex v11 = new Vertex(map.numVertices(), p11, 90); //Centro Comercial de Cedofeita
        v11.setName("Centro Comercial de Cedofeita");
        map.insertVertex(v11);
        Pair<Double, Double> p12 = Pair.createPair(41.15776, -8.62787);
        Vertex v12 = new Vertex(map.numVertices(), p12, 93); //Shopping Brasilia - Associação Comerciantes
        v12.setName("Shopping Brasilia - Associação Comerciantes");
        map.insertVertex(v12);
        Pair<Double, Double> p13 = Pair.createPair(41.16796, -8.63018);
        Vertex v13 = new Vertex(map.numVertices(), p13, 95); //Centro Comercial do Carvalhido
        v13.setName("Centro Comercial do Carvalhido");
        map.insertVertex(v13);
        Pair<Double, Double> p14 = Pair.createPair(41.18252, -8.69221);
        Vertex v14 = new Vertex(map.numVertices(), p14, 12); //Centro Comercial New City
        v14.setName("Centro Comercial New City");
        map.insertVertex(v14);
        Pair<Double, Double> p15 = Pair.createPair(41.18476, -8.68214);
        Vertex v15 = new Vertex(map.numVertices(), p15, 33); //centro comercial parque
        v15.setName("Centro Comercial Parque");
        map.insertVertex(v15);
        Pair<Double, Double> p16 = Pair.createPair(41.15364, -8.60475);
        Vertex v16 = new Vertex(map.numVertices(), p16, 120); //Traditional Shopping Street
        v16.setName("Traditional Shopping Street");
        map.insertVertex(v16);
        Pair<Double, Double> p17 = Pair.createPair(41.20917, -8.68731);
        Vertex v17 = new Vertex(map.numVertices(), p17, 48); //MAR Shopping Matosinhos
        v17.setName("MAR Shopping Matosinhos");
        map.insertVertex(v17);
        Pair<Double, Double> p18 = Pair.createPair(41.18100, -8.65459);
        Vertex v18 = new Vertex(map.numVertices(), p18, 89); //NorteShopping
        v18.setName("NorteShopping");
        map.insertVertex(v18);
        Pair<Double, Double> p19 = Pair.createPair(41.16392, -8.58421);
        Vertex v19 = new Vertex(map.numVertices(), p19, 120); //Alameda Shop & Spot
        v19.setName("Alameda Shop & Spot");
        map.insertVertex(v19);
        Pair<Double, Double> p20 = Pair.createPair(41.14818, -8.61590);
        Vertex v20 = new Vertex(map.numVertices(), p20, 92); //Shop
        v20.setName("Shop");
        map.insertVertex(v20);
        Pair<Double, Double> p21 = Pair.createPair(41.14385, -8.60333);
        Vertex v21 = new Vertex(map.numVertices(), p21, 87); //Espiral Colossal
        v21.setName("Espiral Colossal");
        map.insertVertex(v21);
        Pair<Double, Double> p22 = Pair.createPair(41.13326, -8.60818);
        Vertex v22 = new Vertex(map.numVertices(), p22, 70); //Praceta Salvador Caetano
        v22.setName("Praceta Salvador Caetano");
        map.insertVertex(v22);
        Pair<Double, Double> p23 = Pair.createPair(41.14170, -8.63645);
        Vertex v23 = new Vertex(map.numVertices(), p23, 92); //ArrábidaShopping
        v23.setName("ArrabidaShopping");
        map.insertVertex(v23);
        Pair<Double, Double> p24 = Pair.createPair(41.19016, -8.49756);
        Vertex v24 = new Vertex(map.numVertices(), p24, 143); //Post Office Valongo
        v24.setName("Post Office Valongo");
        map.insertVertex(v24);
        Pair<Double, Double> p25 = Pair.createPair(41.20278, -8.54496);
        Vertex v25 = new Vertex(map.numVertices(), p25, 138); //Centro de Distribuição dos Correios de Ermesinde
        v25.setName("Centro de Distribuição dos Correios de Ermesinde");
        map.insertVertex(v25);
        Pair<Double, Double> p26 = Pair.createPair(41.19274, -8.57594);
        Vertex v26 = new Vertex(map.numVertices(), p26, 143); //Posto Correios Forno
        v26.setName("Posto Correios Forno");
        map.insertVertex(v26);
        Pair<Double, Double> p27 = Pair.createPair(41.17136, -8.58824);
        Vertex v27 = new Vertex(map.numVertices(), p27, 147); //Posto de Correios de Conde Ferreira
        v27.setName("Posto de Correios de Conde Ferreira");
        map.insertVertex(v27);
        Pair<Double, Double> p28 = Pair.createPair(41.17169, -8.61287);
        Vertex v28 = new Vertex(map.numVertices(), p28, 132); //CTT
        v28.setName("CTT");
        map.insertVertex(v28);
        Pair<Double, Double> p29 = Pair.createPair(41.15292, -8.63106);
        Vertex v29 = new Vertex(map.numVertices(), p29, 84); //Correios CTT Campo Alegre
        v29.setName("Correios CTT Campo Alegre");
        map.insertVertex(v29);
        Pair<Double, Double> p30 = Pair.createPair(41.17266, -8.65494);
        Vertex v30 = new Vertex(map.numVertices(), p30, 75); //Pereiró Post Office
        v30.setName("Pereiró Post Office");
        map.insertVertex(v30);
        Pair<Double, Double> p31 = Pair.createPair(41.15406, -8.54706);
        Vertex v31 = new Vertex(map.numVertices(), p31, 85); //Tecofix Porto
        v31.setName("Tecofix Porto");
        map.insertVertex(v31);
        Pair<Double, Double> p32 = Pair.createPair(41.14448, -8.57526);
        Vertex v32 = new Vertex(map.numVertices(), p32, 16); //FISHISCO Pesca, Lda.
        v32.setName("FISHISCO Pesca, Lda.");
        map.insertVertex(v32);

        //edge restriction insertion
        if(!isDrone) {
            Edge e1 = new Edge("Edge1", GraphOperations.calculateDistance(v0.getCoordinates(), v15.getCoordinates()), v0, v15, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e2 = new Edge("Edge2", GraphOperations.calculateDistance(v30.getCoordinates(), v18.getCoordinates()), v30, v18, isDrone, Constants.DEFAULT_WIND_DIRECTION_OPPOSITE);
            Edge e3 = new Edge("Edge3", GraphOperations.calculateDistance(v1.getCoordinates(), v23.getCoordinates()), v1, v23, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e4 = new Edge("Edge4", GraphOperations.calculateDistance(v23.getCoordinates(), v11.getCoordinates()), v23, v11, isDrone, Constants.DEFAULT_WIND_DIRECTION_OPPOSITE);
            Edge e5 = new Edge("Edge5", GraphOperations.calculateDistance(v20.getCoordinates(), v11.getCoordinates()), v20, v11, isDrone, Constants.DEFAULT_WIND_DIRECTION_OPPOSITE);
            Edge e6 = new Edge("Edge6", GraphOperations.calculateDistance(v29.getCoordinates(), v12.getCoordinates()), v29, v12, isDrone, Constants.DEFAULT_WIND_DIRECTION_OPPOSITE);
            Edge e7 = new Edge("Edge7", GraphOperations.calculateDistance(v10.getCoordinates(), v21.getCoordinates()), v10, v21, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e8 = new Edge("Edge8", GraphOperations.calculateDistance(v4.getCoordinates(), v13.getCoordinates()), v4, v13, isDrone, Constants.DEFAULT_WIND_DIRECTION_OPPOSITE);
            Edge e9 = new Edge("Edge9", GraphOperations.calculateDistance(v4.getCoordinates(), v27.getCoordinates()), v4, v27, isDrone, Constants.DEFAULT_WIND_DIRECTION_OPPOSITE);
            Edge e10 = new Edge("Edge10", GraphOperations.calculateDistance(v28.getCoordinates(), v16.getCoordinates()), v28, v16, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e11 = new Edge("Edge10", GraphOperations.calculateDistance(v27.getCoordinates(), v19.getCoordinates()), v27, v19, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e12 = new Edge("Edge12", GraphOperations.calculateDistance(v26.getCoordinates(), v19.getCoordinates()), v26, v19, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            map.insertEdge(e1.getVOrig(), e1.getVDest(), e1);
            map.insertEdge(e2.getVOrig(), e2.getVDest(), e2);
            map.insertEdge(e3.getVOrig(), e3.getVDest(), e3);
            map.insertEdge(e4.getVOrig(), e4.getVDest(), e4);
            map.insertEdge(e5.getVOrig(), e5.getVDest(), e5);
            map.insertEdge(e6.getVOrig(), e6.getVDest(), e6);
            map.insertEdge(e7.getVOrig(), e7.getVDest(), e7);
            map.insertEdge(e8.getVOrig(), e8.getVDest(), e8);
            map.insertEdge(e9.getVOrig(), e9.getVDest(), e9);
            map.insertEdge(e10.getVOrig(), e10.getVDest(), e10);
            map.insertEdge(e11.getVOrig(), e11.getVDest(), e11);
            map.insertEdge(e12.getVOrig(), e12.getVDest(), e12);
        }else{
            Edge e1 = new Edge("Edge1", GraphOperations.calculateDistance(v0.getCoordinates(), v18.getCoordinates()), v0, v18, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e2 = new Edge("Edge2", GraphOperations.calculateDistance(v30.getCoordinates(), v14.getCoordinates()), v30, v14, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e3 = new Edge("Edge3", GraphOperations.calculateDistance(v12.getCoordinates(), v20.getCoordinates()), v12, v20, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e4 = new Edge("Edge4", GraphOperations.calculateDistance(v5.getCoordinates(), v26.getCoordinates()), v5, v26, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e5 = new Edge("Edge5", GraphOperations.calculateDistance(v28.getCoordinates(), v27.getCoordinates()), v28, v27, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e6 = new Edge("Edge6", GraphOperations.calculateDistance(v27.getCoordinates(), v19.getCoordinates()), v27, v19, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e7 = new Edge("Edge7", GraphOperations.calculateDistance(v4.getCoordinates(), v27.getCoordinates()), v4, v27, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e8 = new Edge("Edge8", GraphOperations.calculateDistance(v19.getCoordinates(), v32.getCoordinates()), v19, v32, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            Edge e9 = new Edge("Edge9", GraphOperations.calculateDistance(v19.getCoordinates(), v16.getCoordinates()), v19, v16, isDrone, Constants.DEFAULT_WIND_DIRECTION);
            map.insertEdge(e1.getVOrig(), e1.getVDest(), e1);
            map.insertEdge(e2.getVOrig(), e2.getVDest(), e2);
            map.insertEdge(e3.getVOrig(), e3.getVDest(), e3);
            map.insertEdge(e4.getVOrig(), e4.getVDest(), e4);
            map.insertEdge(e5.getVOrig(), e5.getVDest(), e5);
            map.insertEdge(e6.getVOrig(), e6.getVDest(), e6);
            map.insertEdge(e7.getVOrig(), e7.getVDest(), e7);
            map.insertEdge(e8.getVOrig(), e8.getVDest(), e8);
            map.insertEdge(e9.getVOrig(), e9.getVDest(), e9);
        }

        //all edge insertion
        for (int i = 0; i < map.numVertices(); i++) {
            for (int j = 0; j < map.numVertices(); j++) {
                if(map.getXY(i,j) == null && map.getXY(j,i) == null && i != j){
                    map.insertEdge(map.vertices().get(i), map.vertices().get(j), new Edge(String.valueOf(i + j), GraphOperations.calculateDistance(map.vertices().get(i).getCoordinates(), map.vertices().get(j).getCoordinates()), map.vertices().get(i), map.vertices().get(j), isDrone, Constants.DEFAULT_WIND_DIRECTION));
                    map.insertEdge(map.vertices().get(j), map.vertices().get(i), new Edge(String.valueOf(j + i), GraphOperations.calculateDistance(map.vertices().get(j).getCoordinates(), map.vertices().get(i).getCoordinates()), map.vertices().get(j), map.vertices().get(i), isDrone, Constants.DEFAULT_WIND_DIRECTION_OPPOSITE));
                }
            }
        }

        //edge restriction removal
        if(!isDrone){
            map.removeEdge(v0, v18);
            map.removeEdge(v18, v0);
            map.removeEdge(v31, v32);
            map.removeEdge(v32, v31);
            map.removeEdge(v7, v24);
            map.removeEdge(v24, v7);
        }else{
            map.removeEdge(v0, v17);
            map.removeEdge(v17, v0);
            map.removeEdge(v4, v16);
            map.removeEdge(v16, v4);
            map.removeEdge(v24, v7);
            map.removeEdge(v7, v24);
        }

    }

    public int getNumPharmacies() {
        return numPharmacies;
    }


    private void insertEdges(Vertex v1, Vertex v2, Edge e){
            map.insertEdge(v1, v2, e);
    }

    public AdjacencyMatrixGraph getMap() {
        return this.map;
    }

    public void setEnergyCost(double carrierWeight){
        List<Edge> allEdges = map.edges();
        for(Edge e : allEdges){
            e.setEnergyCost(carrierWeight);
        }
    }

    public boolean isDrone() {
        return isDrone;
    }
}
