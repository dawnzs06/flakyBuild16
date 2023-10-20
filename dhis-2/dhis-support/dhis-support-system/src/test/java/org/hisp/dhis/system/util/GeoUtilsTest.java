/*
 * Copyright (c) 2004-2022, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.hisp.dhis.system.util;

import static org.hisp.dhis.system.util.GeoUtils.getBoxShape;
import static org.hisp.dhis.system.util.GeoUtils.replaceUnsafeSvgText;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.geotools.geojson.geom.GeometryJSON;
import org.hisp.dhis.organisationunit.FeatureType;
import org.hisp.dhis.utils.TestResourceUtils;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;

/** Lars Helge Overland */
class GeoUtilsTest {

  private static final double DELTA = 0.01;

  @Test
  void testGetBoxShape() {
    // Equator
    double[] box = getBoxShape(0, 0, 110574.27);

    assertEquals(1d, box[0], DELTA);
    assertEquals(1d, box[1], DELTA);
    assertEquals(-1d, box[2], DELTA);
    assertEquals(-1d, box[3], DELTA);

    // Punta Arenas

    box = getBoxShape(-71, -53, 67137.20);

    assertEquals(-52.4, box[0], DELTA);
    assertEquals(-70d, box[1], DELTA);
    assertEquals(-53.6, box[2], DELTA);
    assertEquals(-72d, box[3], DELTA);
  }

  @Test
  void testReplaceUnsafeSvgText() {
    String text =
        "<svg xmlns=\"http://www.w3.org/2000/svg\">"
            + "<text id=\"ext-sprite-1866\" zIndex=\"500\" text=\"Measles Coverage <1y\" font=\"bold 18px Arial,Sans-serif,Lucida Grande\" hidden=\"false\">"
            + "<text id=\"ext-sprite-1866\" zIndex=\"500\" text=\"BCG & DPT Coverage\" font=\"bold 18px Arial,Sans-serif,Lucida Grande\" hidden=\"false\">"
            + "</svg>";
    String expected =
        "<svg xmlns=\"http://www.w3.org/2000/svg\">"
            + "<text id=\"ext-sprite-1866\" zIndex=\"500\" text=\"Measles Coverage 1y\" hidden=\"false\">"
            + "<text id=\"ext-sprite-1866\" zIndex=\"500\" text=\"BCG  DPT Coverage\" hidden=\"false\">"
            + "</svg>";
    String actual = replaceUnsafeSvgText(text);
    assertEquals(expected, actual);
  }

  @Test
  void testGetGeometryPointFromCoordinatesAndType() throws IOException {

    Geometry g = GeoUtils.getGeometryFromCoordinatesAndType(FeatureType.POINT, "[-11.2411,8.542]");

    assertEquals("Point", g.getGeometryType());
    assertTrue(g.isValid());
  }

  @Test
  void testGetGeometryPolygonFromCoordinatesAndType() throws IOException {

    Geometry g =
        GeoUtils.getGeometryFromCoordinatesAndType(
            FeatureType.POLYGON,
            "[[[[-10.7788,8.0788],[-10.7789,8.0742],[-10.7799,8.071],[-10.7837,8.0635],[-10.7876,8.0585],[-10.7888,8.0565],[-10.7896,8.0535],[-10.7898,8.0454],[-10.7905,8.0419],[-10.7924,8.0389],[-10.7983,8.0326],[-10.8035,8.0258],[-10.8067,8.024],[-10.8124,8.0189],[-10.8152,8.0167],[-10.8198,8.0143],[-10.8226,8.0121],[-10.8273,8.0077],[-10.83,8.0056],[-10.835,8.0029],[-10.8389,7.9997],[-10.8421,7.9965],[-10.8442,7.9937],[-10.8482,7.9853],[-10.8495,7.9794],[-10.8504,7.9771],[-10.8537,7.9703],[-10.8559,7.9674],[-10.858,7.9655],[-10.8603,7.9639],[-10.8676,7.9605],[-10.8704,7.9598],[-10.8786,7.9594],[-10.8821,7.9588],[-10.8874,7.9566],[-10.8916,7.9557],[-10.8937,7.9549],[-10.8963,7.9532],[-10.8999,7.9497],[-10.9034,7.9455],[-10.9058,7.9405],[-10.9094,7.9346],[-10.9133,7.9318],[-10.9176,7.9283],[-10.9198,7.9268],[-10.923,7.9254],[-10.9273,7.923],[-10.9305,7.9217],[-10.939,7.9172],[-10.943,7.9139],[-10.947,7.9098],[-10.951,7.9054],[-10.9541,7.9011],[-10.958,7.8984],[-10.9613,7.8952],[-10.9635,7.8923],[-10.9652,7.8884],[-10.9689,7.8822],[-10.9752,7.8785],[-10.9791,7.8787],[-10.9818,7.8809],[-10.9843,7.8838],[-10.9883,7.8853],[-10.9905,7.8864],[-10.993,7.8891],[-10.9944,7.8942],[-10.9953,7.8966],[-10.9975,7.899],[-11.0008,7.9001],[-11.0035,7.9],[-11.0061,7.8991],[-11.0092,7.8964],[-11.0118,7.8931],[-11.0136,7.8891],[-11.0158,7.8848],[-11.0171,7.8817],[-11.0194,7.8785],[-11.0239,7.8742],[-11.028,7.8716],[-11.0329,7.8688],[-11.0411,7.8658],[-11.0465,7.8658],[-11.0526,7.8679],[-11.0484,7.8746],[-11.0432,7.8816],[-11.0416,7.8848],[-11.036,7.9039],[-11.0346,7.9096],[-11.0343,7.9142],[-11.0351,7.9199],[-11.035,7.9234],[-11.0343,7.9268],[-11.0317,7.9322],[-11.0297,7.9354],[-11.0221,7.9451],[-11.0174,7.9547],[-11.0131,7.9601],[-11.0049,7.9685],[-11.0006,7.9735],[-10.9958,7.9822],[-10.9939,7.9892],[-10.9931,8.0016],[-10.9922,8.0056],[-10.9865,8.0162],[-10.9803,8.0246],[-10.9767,8.0279],[-10.9742,8.0308],[-10.97,8.038],[-10.9665,8.0455],[-10.9648,8.0513],[-10.9639,8.0649],[-10.9626,8.0693],[-10.9598,8.0723],[-10.9502,8.0781],[-10.9474,8.0814],[-10.9415,8.0916],[-10.9381,8.0952],[-10.9351,8.0975],[-10.929,8.1001],[-10.9268,8.1017],[-10.9227,8.1077],[-10.9199,8.1128],[-10.9151,8.1201],[-10.9124,8.1265],[-10.9105,8.1328],[-10.904,8.1278],[-10.9016,8.1258],[-10.8989,8.1231],[-10.8966,8.1202],[-10.8947,8.1163],[-10.8923,8.112],[-10.891,8.1088],[-10.8882,8.1028],[-10.887,8.0982],[-10.8827,8.0894],[-10.8803,8.0864],[-10.8761,8.0822],[-10.8731,8.0798],[-10.8655,8.0761],[-10.8633,8.0754],[-10.858,8.075],[-10.844,8.0751],[-10.8381,8.0748],[-10.8353,8.0742],[-10.8308,8.0722],[-10.8271,8.0715],[-10.8222,8.0713],[-10.8112,8.0712],[-10.8053,8.0714],[-10.802,8.072],[-10.8,8.0728],[-10.7933,8.0762],[-10.7883,8.0802],[-10.786,8.0816],[-10.7826,8.0825],[-10.7788,8.0788]]]]");
    assertEquals("Polygon", g.getGeometryType());
    assertTrue(g.isValid());
  }

  @Test
  void testGetGeometryMultiPolygonFromCoordinatesAndType() throws IOException {

    Geometry g =
        GeoUtils.getGeometryFromCoordinatesAndType(
            FeatureType.MULTI_POLYGON,
            "[[[[-13.1296,8.539],[-13.1293,8.5379],[-13.1307,8.5363],[-13.1318,8.5371],[-13.1315,8.5399],[-13.1296,8.5399],[-13.1296,8.539]]],[[[-13.1157,8.5487],[-13.1154,8.546],[-13.116,8.5446],[-13.1179,8.5429],[-13.1193,8.5404],[-13.1201,8.5382],[-13.121,8.5376],[-13.1229,8.5376],[-13.1238,8.5387],[-13.1243,8.5418],[-13.1251,8.5424],[-13.1246,8.5454],[-13.1235,8.5468],[-13.1207,8.5468],[-13.1196,8.5479],[-13.1182,8.5485],[-13.1168,8.5499],[-13.1157,8.5499],[-13.1157,8.5487]]],[[[-13.1074,8.5943],[-13.1068,8.5907],[-13.106,8.5882],[-13.1024,8.5868],[-13.1024,8.5854],[-13.1032,8.584],[-13.1035,8.5821],[-13.1054,8.5801],[-13.1057,8.5788],[-13.1071,8.5774],[-13.1076,8.5751],[-13.1096,8.5732],[-13.109,8.5713],[-13.111,8.5696],[-13.1107,8.5685],[-13.109,8.5688],[-13.1088,8.5682],[-13.1065,8.5679],[-13.106,8.5668],[-13.1049,8.5665],[-13.1051,8.5635],[-13.1046,8.5618],[-13.1065,8.5588],[-13.1088,8.5585],[-13.1088,8.5574],[-13.1115,8.5571],[-13.1115,8.5601],[-13.1121,8.5601],[-13.1126,8.5618],[-13.1132,8.5618],[-13.1138,8.5665],[-13.1149,8.5665],[-13.1149,8.5651],[-13.1165,8.5638],[-13.1188,8.564],[-13.1196,8.5657],[-13.121,8.566],[-13.1215,8.5643],[-13.1199,8.5643],[-13.1196,8.5601],[-13.1199,8.5593],[-13.1224,8.5588],[-13.1224,8.5599],[-13.1235,8.5624],[-13.1229,8.5643],[-13.124,8.5643],[-13.1246,8.5671],[-13.1246,8.5688],[-13.1238,8.5704],[-13.1226,8.5713],[-13.1226,8.5738],[-13.1213,8.5738],[-13.1201,8.5757],[-13.1199,8.5774],[-13.1185,8.5793],[-13.1182,8.5813],[-13.1171,8.5818],[-13.1165,8.5829],[-13.1165,8.5846],[-13.116,8.5849],[-13.1157,8.5865],[-13.1146,8.5868],[-13.114,8.5888],[-13.1124,8.5907],[-13.1124,8.5921],[-13.1112,8.5937],[-13.1074,8.5943]]],[[[-13.1082,8.6001],[-13.1076,8.5993],[-13.1076,8.5971],[-13.1082,8.5965],[-13.1099,8.5965],[-13.1096,8.5993],[-13.1082,8.6001]]],[[[-13.1529,8.6612],[-13.1549,8.6593],[-13.1557,8.6576],[-13.1563,8.6576],[-13.1565,8.656],[-13.1574,8.6546],[-13.1601,8.6543],[-13.1593,8.6535],[-13.1574,8.6504],[-13.1568,8.6504],[-13.1568,8.6474],[-13.1599,8.6465],[-13.1621,8.6465],[-13.1629,8.6474],[-13.1643,8.6474],[-13.1643,8.6465],[-13.1615,8.6454],[-13.1579,8.6454],[-13.1571,8.646],[-13.1563,8.6451],[-13.1554,8.6429],[-13.1526,8.6407],[-13.1529,8.6382],[-13.1529,8.6351],[-13.1513,8.6357],[-13.1513,8.6388],[-13.1507,8.6401],[-13.1451,8.6401],[-13.1418,8.639],[-13.1401,8.639],[-13.1393,8.6385],[-13.1374,8.6385],[-13.1349,8.6374],[-13.1338,8.6374],[-13.1313,8.636],[-13.1288,8.6351],[-13.1288,8.6346],[-13.126,8.6324],[-13.1251,8.6307],[-13.1251,8.6288],[-13.1246,8.6288],[-13.1243,8.6268],[-13.1229,8.6237],[-13.1221,8.6232],[-13.1218,8.6215],[-13.1235,8.6215],[-13.1249,8.6229],[-13.1268,8.6235],[-13.1271,8.624],[-13.1296,8.624],[-13.1301,8.6235],[-13.1321,8.6232],[-13.1332,8.6224],[-13.1351,8.6218],[-13.1371,8.6199],[-13.1382,8.6193],[-13.1393,8.6196],[-13.1396,8.6204],[-13.1407,8.6204],[-13.1404,8.6187],[-13.139,8.6179],[-13.1363,8.6182],[-13.1349,8.6196],[-13.1326,8.6196],[-13.1299,8.621],[-13.1265,8.6212],[-13.126,8.6201],[-13.124,8.6187],[-13.1221,8.6179],[-13.1213,8.6165],[-13.1182,8.6149],[-13.1168,8.6135],[-13.1157,8.6132],[-13.1146,8.6121],[-13.1135,8.6118],[-13.111,8.6101],[-13.1093,8.6065],[-13.1088,8.6035],[-13.1093,8.6024],[-13.1093,8.6007],[-13.1112,8.5985],[-13.1135,8.5954],[-13.1143,8.5926],[-13.1149,8.5921],[-13.1154,8.5893],[-13.1171,8.5888],[-13.1174,8.5857],[-13.1185,8.5849],[-13.119,8.5832],[-13.1218,8.579],[-13.1238,8.5774],[-13.1257,8.5735],[-13.1282,8.5701],[-13.1282,8.5685],[-13.1288,8.5663],[-13.1282,8.5651],[-13.1282,8.5613],[-13.1276,8.556],[-13.1282,8.5543],[-13.1282,8.5524],[-13.1301,8.5499],[-13.1315,8.549],[-13.1326,8.549],[-13.1354,8.5463],[-13.1368,8.5465],[-13.1371,8.546],[-13.1354,8.5454],[-13.1338,8.5471],[-13.1326,8.5476],[-13.1324,8.546],[-13.1338,8.5443],[-13.1343,8.5418],[-13.1349,8.5418],[-13.1351,8.5399],[-13.1351,8.5337],[-13.1365,8.5296],[-13.1374,8.529],[-13.139,8.526],[-13.1401,8.5257],[-13.141,8.5243],[-13.1424,8.5243],[-13.144,8.5229],[-13.1443,8.5221],[-13.1454,8.5221],[-13.1471,8.5207],[-13.1468,8.5162],[-13.1482,8.5162],[-13.1496,8.5179],[-13.151,8.5179],[-13.1529,8.5196],[-13.1554,8.5199],[-13.156,8.5221],[-13.1568,8.5224],[-13.1599,8.5257],[-13.1626,8.5257],[-13.1632,8.5265],[-13.1674,8.5265],[-13.1693,8.5263],[-13.1704,8.5271],[-13.1713,8.531],[-13.1724,8.5326],[-13.1724,8.5346],[-13.1732,8.536],[-13.1732,8.5371],[-13.1751,8.5382],[-13.1765,8.5399],[-13.1765,8.5415],[-13.1774,8.5424],[-13.1785,8.5424],[-13.1815,8.5465],[-13.1826,8.5471],[-13.184,8.5499],[-13.1851,8.5504],[-13.1863,8.5529],[-13.1874,8.5535],[-13.1876,8.5549],[-13.1887,8.5565],[-13.1893,8.5585],[-13.1904,8.559],[-13.1904,8.5604],[-13.1915,8.5626],[-13.1921,8.5626],[-13.1943,8.566],[-13.1951,8.5682],[-13.196,8.569],[-13.1965,8.571],[-13.1974,8.5715],[-13.1982,8.5735],[-13.1982,8.5746],[-13.199,8.5751],[-13.199,8.5771],[-13.1999,8.579],[-13.2007,8.5838],[-13.2015,8.5854],[-13.2015,8.5879],[-13.2021,8.5885],[-13.2021,8.5901],[-13.2026,8.5904],[-13.2029,8.5937],[-13.2038,8.5943],[-13.204,8.5965],[-13.2046,8.5974],[-13.2051,8.6004],[-13.2065,8.6021],[-13.2068,8.604],[-13.2074,8.6046],[-13.2085,8.6088],[-13.2099,8.6104],[-13.2099,8.6113],[-13.2113,8.6143],[-13.2118,8.6165],[-13.2124,8.6165],[-13.2137,8.6185],[-13.2143,8.6204],[-13.2154,8.6212],[-13.216,8.6229],[-13.2165,8.6229],[-13.2171,8.6246],[-13.2182,8.6254],[-13.2182,8.6262],[-13.2196,8.6274],[-13.2196,8.6288],[-13.2204,8.6299],[-13.2218,8.6307],[-13.2221,8.6321],[-13.2232,8.6326],[-13.2238,8.6343],[-13.2265,8.6371],[-13.2276,8.6371],[-13.229,8.6379],[-13.2299,8.6393],[-13.2299,8.6407],[-13.231,8.6418],[-13.2313,8.6432],[-13.2332,8.644],[-13.2351,8.6462],[-13.2354,8.6476],[-13.2368,8.649],[-13.2368,8.6501],[-13.2385,8.6526],[-13.2385,8.6537],[-13.2399,8.6568],[-13.2404,8.6568],[-13.2407,8.6588],[-13.2418,8.6604],[-13.2426,8.6607],[-13.2432,8.6626],[-13.2426,8.6635],[-13.2426,8.6665],[-13.2432,8.6674],[-13.2432,8.6707],[-13.2443,8.6726],[-13.2446,8.6751],[-13.244,8.6763],[-13.2443,8.6788],[-13.2451,8.6801],[-13.244,8.681],[-13.2437,8.6854],[-13.2426,8.6863],[-13.2424,8.6874],[-13.2432,8.691],[-13.2418,8.6915],[-13.2413,8.6924],[-13.2413,8.6954],[-13.2415,8.6954],[-13.2415,8.6985],[-13.241,8.7001],[-13.2413,8.7046],[-13.2418,8.7065],[-13.2418,8.7088],[-13.2429,8.7121],[-13.244,8.7179],[-13.2443,8.7235],[-13.2449,8.724],[-13.2443,8.7299],[-13.2435,8.7321],[-13.2429,8.736],[-13.2415,8.7363],[-13.241,8.7357],[-13.2404,8.7329],[-13.2404,8.7312],[-13.2393,8.7285],[-13.2393,8.726],[-13.241,8.721],[-13.2418,8.7201],[-13.2418,8.719],[-13.2404,8.7163],[-13.2368,8.7121],[-13.2376,8.7093],[-13.2379,8.7049],[-13.2374,8.7029],[-13.236,8.701],[-13.2349,8.7001],[-13.2343,8.7009],[-13.2257,8.7006],[-13.2191,8.7032],[-13.2126,8.7041],[-13.2091,8.705],[-13.2062,8.7072],[-13.2036,8.7105],[-13.2014,8.7144],[-13.1983,8.7179],[-13.195,8.7194],[-13.1861,8.7244],[-13.1839,8.7288],[-13.1832,8.7357],[-13.1808,8.7404],[-13.1769,8.7439],[-13.1698,8.7468],[-13.1659,8.7454],[-13.1631,8.7429],[-13.1602,8.7385],[-13.1563,8.7357],[-13.1527,8.7327],[-13.1491,8.7308],[-13.1408,8.7296],[-13.1377,8.7282],[-13.1356,8.7263],[-13.1319,8.7193],[-13.1309,8.7147],[-13.1312,8.7094],[-13.134,8.7052],[-13.1398,8.7018],[-13.1432,8.6991],[-13.1473,8.6953],[-13.1507,8.6915],[-13.1527,8.6875],[-13.154,8.6787],[-13.1564,8.6724],[-13.1567,8.6674],[-13.1562,8.6641],[-13.1537,8.6607],[-13.1529,8.6612]]]]");
    assertEquals("MultiPolygon", g.getGeometryType());
    assertTrue(g.isValid());
  }

  @Test
  void testGetCoordinatesFromGeometryMultiPolygon() throws IOException {
    String _multipolygon =
        "[[[[-13.1296,8.539],[-13.1293,8.5379],[-13.1307,8.5363],[-13.1318,8.5371],[-13.1315,8.5399],[-13.1296,8.5399],[-13.1296,8.539]]],[[[-13.1157,8.5487],[-13.1154,8.546],[-13.116,8.5446],[-13.1179,8.5429],[-13.1193,8.5404],[-13.1201,8.5382],[-13.121,8.5376],[-13.1229,8.5376],[-13.1238,8.5387],[-13.1243,8.5418],[-13.1251,8.5424],[-13.1246,8.5454],[-13.1235,8.5468],[-13.1207,8.5468],[-13.1196,8.5479],[-13.1182,8.5485],[-13.1168,8.5499],[-13.1157,8.5499],[-13.1157,8.5487]]],[[[-13.1074,8.5943],[-13.1068,8.5907],[-13.106,8.5882],[-13.1024,8.5868],[-13.1024,8.5854],[-13.1032,8.584],[-13.1035,8.5821],[-13.1054,8.5801],[-13.1057,8.5788],[-13.1071,8.5774],[-13.1076,8.5751],[-13.1096,8.5732],[-13.109,8.5713],[-13.111,8.5696],[-13.1107,8.5685],[-13.109,8.5688],[-13.1088,8.5682],[-13.1065,8.5679],[-13.106,8.5668],[-13.1049,8.5665],[-13.1051,8.5635],[-13.1046,8.5618],[-13.1065,8.5588],[-13.1088,8.5585],[-13.1088,8.5574],[-13.1115,8.5571],[-13.1115,8.5601],[-13.1121,8.5601],[-13.1126,8.5618],[-13.1132,8.5618],[-13.1138,8.5665],[-13.1149,8.5665],[-13.1149,8.5651],[-13.1165,8.5638],[-13.1188,8.564],[-13.1196,8.5657],[-13.121,8.566],[-13.1215,8.5643],[-13.1199,8.5643],[-13.1196,8.5601],[-13.1199,8.5593],[-13.1224,8.5588],[-13.1224,8.5599],[-13.1235,8.5624],[-13.1229,8.5643],[-13.124,8.5643],[-13.1246,8.5671],[-13.1246,8.5688],[-13.1238,8.5704],[-13.1226,8.5713],[-13.1226,8.5738],[-13.1213,8.5738],[-13.1201,8.5757],[-13.1199,8.5774],[-13.1185,8.5793],[-13.1182,8.5813],[-13.1171,8.5818],[-13.1165,8.5829],[-13.1165,8.5846],[-13.116,8.5849],[-13.1157,8.5865],[-13.1146,8.5868],[-13.114,8.5888],[-13.1124,8.5907],[-13.1124,8.5921],[-13.1112,8.5937],[-13.1074,8.5943]]],[[[-13.1082,8.6001],[-13.1076,8.5993],[-13.1076,8.5971],[-13.1082,8.5965],[-13.1099,8.5965],[-13.1096,8.5993],[-13.1082,8.6001]]],[[[-13.1529,8.6612],[-13.1549,8.6593],[-13.1557,8.6576],[-13.1563,8.6576],[-13.1565,8.656],[-13.1574,8.6546],[-13.1601,8.6543],[-13.1593,8.6535],[-13.1574,8.6504],[-13.1568,8.6504],[-13.1568,8.6474],[-13.1599,8.6465],[-13.1621,8.6465],[-13.1629,8.6474],[-13.1643,8.6474],[-13.1643,8.6465],[-13.1615,8.6454],[-13.1579,8.6454],[-13.1571,8.646],[-13.1563,8.6451],[-13.1554,8.6429],[-13.1526,8.6407],[-13.1529,8.6382],[-13.1529,8.6351],[-13.1513,8.6357],[-13.1513,8.6388],[-13.1507,8.6401],[-13.1451,8.6401],[-13.1418,8.639],[-13.1401,8.639],[-13.1393,8.6385],[-13.1374,8.6385],[-13.1349,8.6374],[-13.1338,8.6374],[-13.1313,8.636],[-13.1288,8.6351],[-13.1288,8.6346],[-13.126,8.6324],[-13.1251,8.6307],[-13.1251,8.6288],[-13.1246,8.6288],[-13.1243,8.6268],[-13.1229,8.6237],[-13.1221,8.6232],[-13.1218,8.6215],[-13.1235,8.6215],[-13.1249,8.6229],[-13.1268,8.6235],[-13.1271,8.624],[-13.1296,8.624],[-13.1301,8.6235],[-13.1321,8.6232],[-13.1332,8.6224],[-13.1351,8.6218],[-13.1371,8.6199],[-13.1382,8.6193],[-13.1393,8.6196],[-13.1396,8.6204],[-13.1407,8.6204],[-13.1404,8.6187],[-13.139,8.6179],[-13.1363,8.6182],[-13.1349,8.6196],[-13.1326,8.6196],[-13.1299,8.621],[-13.1265,8.6212],[-13.126,8.6201],[-13.124,8.6187],[-13.1221,8.6179],[-13.1213,8.6165],[-13.1182,8.6149],[-13.1168,8.6135],[-13.1157,8.6132],[-13.1146,8.6121],[-13.1135,8.6118],[-13.111,8.6101],[-13.1093,8.6065],[-13.1088,8.6035],[-13.1093,8.6024],[-13.1093,8.6007],[-13.1112,8.5985],[-13.1135,8.5954],[-13.1143,8.5926],[-13.1149,8.5921],[-13.1154,8.5893],[-13.1171,8.5888],[-13.1174,8.5857],[-13.1185,8.5849],[-13.119,8.5832],[-13.1218,8.579],[-13.1238,8.5774],[-13.1257,8.5735],[-13.1282,8.5701],[-13.1282,8.5685],[-13.1288,8.5663],[-13.1282,8.5651],[-13.1282,8.5613],[-13.1276,8.556],[-13.1282,8.5543],[-13.1282,8.5524],[-13.1301,8.5499],[-13.1315,8.549],[-13.1326,8.549],[-13.1354,8.5463],[-13.1368,8.5465],[-13.1371,8.546],[-13.1354,8.5454],[-13.1338,8.5471],[-13.1326,8.5476],[-13.1324,8.546],[-13.1338,8.5443],[-13.1343,8.5418],[-13.1349,8.5418],[-13.1351,8.5399],[-13.1351,8.5337],[-13.1365,8.5296],[-13.1374,8.529],[-13.139,8.526],[-13.1401,8.5257],[-13.141,8.5243],[-13.1424,8.5243],[-13.144,8.5229],[-13.1443,8.5221],[-13.1454,8.5221],[-13.1471,8.5207],[-13.1468,8.5162],[-13.1482,8.5162],[-13.1496,8.5179],[-13.151,8.5179],[-13.1529,8.5196],[-13.1554,8.5199],[-13.156,8.5221],[-13.1568,8.5224],[-13.1599,8.5257],[-13.1626,8.5257],[-13.1632,8.5265],[-13.1674,8.5265],[-13.1693,8.5263],[-13.1704,8.5271],[-13.1713,8.531],[-13.1724,8.5326],[-13.1724,8.5346],[-13.1732,8.536],[-13.1732,8.5371],[-13.1751,8.5382],[-13.1765,8.5399],[-13.1765,8.5415],[-13.1774,8.5424],[-13.1785,8.5424],[-13.1815,8.5465],[-13.1826,8.5471],[-13.184,8.5499],[-13.1851,8.5504],[-13.1863,8.5529],[-13.1874,8.5535],[-13.1876,8.5549],[-13.1887,8.5565],[-13.1893,8.5585],[-13.1904,8.559],[-13.1904,8.5604],[-13.1915,8.5626],[-13.1921,8.5626],[-13.1943,8.566],[-13.1951,8.5682],[-13.196,8.569],[-13.1965,8.571],[-13.1974,8.5715],[-13.1982,8.5735],[-13.1982,8.5746],[-13.199,8.5751],[-13.199,8.5771],[-13.1999,8.579],[-13.2007,8.5838],[-13.2015,8.5854],[-13.2015,8.5879],[-13.2021,8.5885],[-13.2021,8.5901],[-13.2026,8.5904],[-13.2029,8.5937],[-13.2038,8.5943],[-13.204,8.5965],[-13.2046,8.5974],[-13.2051,8.6004],[-13.2065,8.6021],[-13.2068,8.604],[-13.2074,8.6046],[-13.2085,8.6088],[-13.2099,8.6104],[-13.2099,8.6113],[-13.2113,8.6143],[-13.2118,8.6165],[-13.2124,8.6165],[-13.2137,8.6185],[-13.2143,8.6204],[-13.2154,8.6212],[-13.216,8.6229],[-13.2165,8.6229],[-13.2171,8.6246],[-13.2182,8.6254],[-13.2182,8.6262],[-13.2196,8.6274],[-13.2196,8.6288],[-13.2204,8.6299],[-13.2218,8.6307],[-13.2221,8.6321],[-13.2232,8.6326],[-13.2238,8.6343],[-13.2265,8.6371],[-13.2276,8.6371],[-13.229,8.6379],[-13.2299,8.6393],[-13.2299,8.6407],[-13.231,8.6418],[-13.2313,8.6432],[-13.2332,8.644],[-13.2351,8.6462],[-13.2354,8.6476],[-13.2368,8.649],[-13.2368,8.6501],[-13.2385,8.6526],[-13.2385,8.6537],[-13.2399,8.6568],[-13.2404,8.6568],[-13.2407,8.6588],[-13.2418,8.6604],[-13.2426,8.6607],[-13.2432,8.6626],[-13.2426,8.6635],[-13.2426,8.6665],[-13.2432,8.6674],[-13.2432,8.6707],[-13.2443,8.6726],[-13.2446,8.6751],[-13.244,8.6763],[-13.2443,8.6788],[-13.2451,8.6801],[-13.244,8.681],[-13.2437,8.6854],[-13.2426,8.6863],[-13.2424,8.6874],[-13.2432,8.691],[-13.2418,8.6915],[-13.2413,8.6924],[-13.2413,8.6954],[-13.2415,8.6954],[-13.2415,8.6985],[-13.241,8.7001],[-13.2413,8.7046],[-13.2418,8.7065],[-13.2418,8.7088],[-13.2429,8.7121],[-13.244,8.7179],[-13.2443,8.7235],[-13.2449,8.724],[-13.2443,8.7299],[-13.2435,8.7321],[-13.2429,8.736],[-13.2415,8.7363],[-13.241,8.7357],[-13.2404,8.7329],[-13.2404,8.7312],[-13.2393,8.7285],[-13.2393,8.726],[-13.241,8.721],[-13.2418,8.7201],[-13.2418,8.719],[-13.2404,8.7163],[-13.2368,8.7121],[-13.2376,8.7093],[-13.2379,8.7049],[-13.2374,8.7029],[-13.236,8.701],[-13.2349,8.7001],[-13.2343,8.7009],[-13.2257,8.7006],[-13.2191,8.7032],[-13.2126,8.7041],[-13.2091,8.705],[-13.2062,8.7072],[-13.2036,8.7105],[-13.2014,8.7144],[-13.1983,8.7179],[-13.195,8.7194],[-13.1861,8.7244],[-13.1839,8.7288],[-13.1832,8.7357],[-13.1808,8.7404],[-13.1769,8.7439],[-13.1698,8.7468],[-13.1659,8.7454],[-13.1631,8.7429],[-13.1602,8.7385],[-13.1563,8.7357],[-13.1527,8.7327],[-13.1491,8.7308],[-13.1408,8.7296],[-13.1377,8.7282],[-13.1356,8.7263],[-13.1319,8.7193],[-13.1309,8.7147],[-13.1312,8.7094],[-13.134,8.7052],[-13.1398,8.7018],[-13.1432,8.6991],[-13.1473,8.6953],[-13.1507,8.6915],[-13.1527,8.6875],[-13.154,8.6787],[-13.1564,8.6724],[-13.1567,8.6674],[-13.1562,8.6641],[-13.1537,8.6607],[-13.1529,8.6612]]]]";
    Geometry g =
        GeoUtils.getGeometryFromCoordinatesAndType(FeatureType.MULTI_POLYGON, _multipolygon);
    String res = GeoUtils.getCoordinatesFromGeometry(g);
    assertEquals(_multipolygon, res);
  }

  @Test
  void testVerifyPointIsWithinPolygon() throws IOException {
    String downtownOslo = TestResourceUtils.getFileContent("gis/downtownOslo.json");
    Geometry g = new GeometryJSON().read(downtownOslo);
    boolean result = GeoUtils.checkPointWithMultiPolygon(10.746517181396484, 59.91080384720672, g);
    assertTrue(result);
    result = GeoUtils.checkPointWithMultiPolygon(10.755915641784668, 59.9139664757207, g);
    assertFalse(result);
  }

  @Test
  void testVerifyPointIsWithinMultiPolygon() throws IOException {
    String downtownOslo = TestResourceUtils.getFileContent("gis/brasilMultiPolygon.json");
    Geometry g = new GeometryJSON().read(downtownOslo);
    boolean result = GeoUtils.checkPointWithMultiPolygon(-43.96728515625, -16.699340234594537, g);
    assertTrue(result);
    result = GeoUtils.checkPointWithMultiPolygon(-43.681640625, -18.698285474146807, g);
    assertFalse(result);
  }
}