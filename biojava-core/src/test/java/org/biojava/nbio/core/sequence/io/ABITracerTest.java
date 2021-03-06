/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 */

package org.biojava.nbio.core.sequence.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.junit.*;

/**
 * Test file 3730.ab1 is from https://github.com/biopython/biopython/blob/master/Tests/Abi/3730.ab1
 * The test data for comparing the results from ABITrace.java for file 3730.ab1 is from https://github.com/biopython/biopython/blob/master/Tests/Abi/test_data
 */
public class ABITracerTest {

	private String sequence = "GGGCGAGCKYYAYATTTTGGCAAGAATTGAGCTCTATGGCCACAACCATGGTGAGCAAGGGCGAGGAGGATAACATGGCCATCATCAAGGAGTTCATGCGCTTCAAGGTGCACATGGAGGGCTCCGTGAACGGCCACGAGTTCGAGATCGAGGGCGAGGGCGAGGGCCGCCCCTACGAGGGCACCCAGACCGCCAAGCTGAAGGTGACCAAGGGTGGCCCCCTGCCCTTCGCCTGGGACATCCTGTCCCCTCAGTTCATGTACGGCTCCAAGGCCTACGTGAAGCACCCCGCCGACATCCCCGACTACTTGAAGCTGTCCTTCCCCGAGGGCTTCAAGTGGGAGCGCGTGATGAACTTCGAGGACGGCGGCGTGGTGACCGTGACCCAGGACTCCTCCCTGCAGGACGGCGAGTTCATCTACAAGGTGAAGCTGCGCGGCACCAACTTCCCCTCCGACGGCCCCGTAATGCAGAAGAAGACCATGGGCTGGGAGGCCTCCTCCGAGCGGATGTACCCCGAGGACGGCGCCCTGAAGGGCGAGATCAAGCAGAGGCTGAAGCTGAAGGACGGCGGCCACTACGACGCTGAGGTCAAGACCACCTACAAGGCCAAGAAGCCCGTGCAGCTGCCCGGCGCCTACAACGTCAACATCAAGTTGGACATCACCTCCCACAACGAGGACTACACCATCGTGGAACAGTACGAACGCGCCGAGGGCCGCCACTCCACCGGCGGCATGGACGAGCTGTACAAGGGCGGCAGCGGCATGGTGAGCAAGGGCGAGGAGCTGTTCACCGGGGTGGTGCCCATCCTGGTCGAGCTGGACGGCGACGTAAACGGCCACAAGTTCAGCGTGTCCGGCGAGGGCGAGGGCGATGCCACCTACGGCAAGCTGACCCTGAAGTTCATCTGCACCACCGGCAAGCTGCCCGTGCCCTGGCCCACCCTCGTGACCACCCTGACCTACGGCGTGCAGTGCTTCAGCCGCTACCCCGACCACATGAAGCAGCACGACTTCTTCAAGTCCGCCATGCCCGAAGGCTACGTCCAGGAGCGCACCATCTTCTTCAAGGACGACGGCAACTACAARACCCGCGCCGAGGTGAARTTCGAGGGCGACACCCTGGTGAACCGCATCGAGCTGAAAGGGGCAYCGCACCTTTC";
	private int[] qual = {20, 3, 4, 4, 4, 6, 4, 4, 0, 0, 0, 6, 0, 10, 20, 26, 22, 17, 21, 31, 29, 32, 28, 18, 23, 17, 19, 35, 36, 50, 39, 50, 50, 50, 50, 50, 25, 35, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 35, 39, 33, 20, 35, 31, 50, 50, 50, 50, 50, 50, 50, 50, 50, 31, 50, 35, 31, 23, 28, 31, 21, 43, 39, 35, 24, 30, 26, 35, 31, 50, 50, 50, 50, 50, 50, 50, 50, 50, 39, 31, 24, 39, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 31, 31, 43, 43, 50, 50, 50, 50, 50, 31, 31, 31, 31, 50, 50, 50, 50, 50, 50, 50, 50, 31, 31, 35, 50, 50, 50, 50, 31, 36, 55, 55, 55, 55, 36, 55, 55, 55, 55, 55, 36, 55, 55, 55, 55, 55, 36, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 40, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 36, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 40, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 55, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 43, 43, 50, 43, 43, 50, 43, 43, 50, 43, 43, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 43, 43, 50, 43, 43, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 28, 28, 35, 28, 28, 35, 28, 28, 35, 28, 28, 35, 28, 28, 35, 28, 21, 28, 35, 28, 28, 35, 35, 35, 35, 35, 37, 38, 21, 28, 35, 28, 28, 35, 35, 35, 35, 35, 35, 35, 36, 36, 21, 39, 35, 35, 35, 39, 35, 37, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 28, 28, 35, 35, 28, 28, 35, 35, 35, 36, 36, 22, 39, 35, 35, 35, 35, 35, 35, 37, 38, 28, 35, 21, 36, 36, 37, 35, 35, 20, 39, 39, 35, 35, 35, 35, 37, 38, 28, 35, 37, 34, 35, 24, 24, 27, 25, 20, 24, 37, 35, 27, 21, 20, 21, 27, 17, 20, 24, 32, 26, 20, 12, 20, 10, 20, 24, 25, 23, 20, 32, 24, 24, 23, 20, 24, 23, 18, 34, 34, 34, 22, 26, 24, 24, 18, 22, 22, 23, 25, 20, 12, 20, 24, 23, 24, 23, 22, 20, 20, 0, 20, 24, 23, 20, 8, 10, 4, 20, 20, 3, 7, 19, 20, 4, 4, 7, 7, 0, 7, 11, 18, 8, 3, 23, 23, 20, 11, 4, 20, 18, 12, 20, 20, 20, 4, 20, 4, 2, 3, 21, 21, 21, 21, 10, 15, 14, 15, 19, 2, 4, 3, 6, 11, 3, 4, 6, 21, 16, 20, 11, 1, 4, 12, 0, 15, 8, 1, 3, 3, 12, 1, 11, 20, 4};
	private int[] base = {2, 13, 38, 51, 67, 78, 92, 118, 138, 162, 181, 191, 210, 222, 239, 253, 266, 280, 288, 304, 317, 333, 347, 359, 375, 386, 394, 406, 418, 433, 444, 457, 472, 482, 496, 506, 519, 529, 544, 557, 569, 579, 590, 601, 614, 626, 638, 649, 663, 673, 686, 706, 715, 731, 740, 753, 765, 777, 787, 799, 813, 826, 838, 854, 863, 876, 892, 901, 913, 929, 937, 948, 960, 970, 981, 993, 1004, 1017, 1034, 1045, 1056, 1068, 1080, 1091, 1103, 1115, 1126, 1138, 1148, 1160, 1177, 1187, 1199, 1211, 1222, 1232, 1243, 1254, 1268, 1279, 1294, 1307, 1319, 1330, 1341, 1352, 1362, 1374, 1388, 1398, 1411, 1422, 1433, 1444, 1456, 1466, 1479, 1497, 1506, 1519, 1531, 1543, 1556, 1567, 1578, 1589, 1604, 1614, 1630, 1641, 1651, 1662, 1675, 1688, 1700, 1711, 1721, 1732, 1748, 1758, 1772, 1784, 1795, 1806, 1820, 1830, 1844, 1855, 1866, 1877, 1892, 1902, 1914, 1926, 1939, 1950, 1965, 1974, 1986, 1999, 2011, 2023, 2037, 2047, 2059, 2072, 2084, 2096, 2107, 2120, 2132, 2144, 2156, 2169, 2180, 2191, 2202, 2217, 2227, 2239, 2251, 2264, 2275, 2286, 2297, 2309, 2321, 2332, 2347, 2358, 2369, 2381, 2394, 2406, 2417, 2429, 2439, 2452, 2465, 2476, 2490, 2501, 2512, 2524, 2536, 2546, 2560, 2570, 2581, 2593, 2605, 2616, 2628, 2640, 2653, 2664, 2676, 2688, 2700, 2712, 2723, 2735, 2748, 2759, 2772, 2784, 2795, 2808, 2820, 2831, 2842, 2854, 2866, 2878, 2888, 2901, 2913, 2927, 2936, 2947, 2958, 2970, 2982, 2994, 3005, 3019, 3030, 3041, 3053, 3064, 3077, 3088, 3099, 3110, 3123, 3135, 3146, 3157, 3168, 3179, 3192, 3203, 3214, 3226, 3238, 3251, 3263, 3275, 3286, 3297, 3308, 3320, 3331, 3344, 3356, 3368, 3380, 3391, 3402, 3415, 3426, 3440, 3451, 3462, 3474, 3485, 3496, 3508, 3520, 3532, 3543, 3556, 3569, 3580, 3593, 3604, 3615, 3626, 3638, 3650, 3661, 3673, 3684, 3698, 3709, 3721, 3732, 3744, 3756, 3767, 3779, 3792, 3803, 3814, 3827, 3838, 3850, 3862, 3873, 3885, 3897, 3909, 3920, 3932, 3943, 3955, 3966, 3980, 3990, 4002, 4014, 4026, 4038, 4050, 4061, 4072, 4083, 4095, 4107, 4119, 4131, 4143, 4156, 4167, 4179, 4191, 4203, 4215, 4227, 4238, 4252, 4262, 4274, 4287, 4298, 4310, 4321, 4333, 4345, 4356, 4370, 4381, 4393, 4406, 4417, 4428, 4440, 4453, 4464, 4477, 4489, 4500, 4513, 4524, 4536, 4548, 4560, 4573, 4583, 4595, 4607, 4620, 4631, 4645, 4655, 4667, 4679, 4690, 4702, 4714, 4728, 4739, 4750, 4762, 4774, 4786, 4798, 4810, 4821, 4833, 4845, 4857, 4869, 4880, 4892, 4905, 4916, 4927, 4940, 4952, 4963, 4977, 4988, 5000, 5012, 5023, 5034, 5045, 5057, 5069, 5081, 5093, 5104, 5115, 5127, 5139, 5151, 5163, 5176, 5188, 5199, 5211, 5223, 5235, 5247, 5259, 5272, 5283, 5296, 5308, 5320, 5331, 5343, 5354, 5366, 5378, 5390, 5402, 5414, 5426, 5438, 5450, 5462, 5474, 5486, 5497, 5510, 5521, 5532, 5544, 5557, 5569, 5581, 5592, 5604, 5617, 5629, 5641, 5652, 5663, 5676, 5687, 5699, 5712, 5724, 5735, 5748, 5760, 5771, 5784, 5794, 5806, 5817, 5829, 5841, 5853, 5866, 5879, 5891, 5903, 5916, 5928, 5941, 5952, 5964, 5976, 5988, 6000, 6012, 6024, 6036, 6048, 6060, 6072, 6085, 6096, 6109, 6121, 6133, 6146, 6157, 6168, 6180, 6192, 6203, 6215, 6227, 6239, 6251, 6265, 6276, 6289, 6302, 6313, 6325, 6337, 6349, 6361, 6374, 6386, 6398, 6410, 6422, 6436, 6448, 6459, 6471, 6483, 6495, 6507, 6520, 6532, 6545, 6555, 6567, 6579, 6591, 6603, 6615, 6627, 6640, 6652, 6664, 6676, 6688, 6700, 6713, 6726, 6738, 6749, 6761, 6774, 6786, 6799, 6811, 6823, 6835, 6848, 6859, 6871, 6883, 6895, 6907, 6920, 6933, 6945, 6956, 6968, 6980, 6992, 7005, 7016, 7030, 7042, 7053, 7066, 7079, 7091, 7104, 7115, 7128, 7140, 7152, 7163, 7175, 7187, 7200, 7212, 7224, 7235, 7248, 7260, 7272, 7285, 7297, 7309, 7321, 7333, 7345, 7358, 7370, 7382, 7394, 7406, 7419, 7431, 7443, 7455, 7468, 7480, 7492, 7505, 7517, 7530, 7542, 7554, 7566, 7578, 7591, 7603, 7615, 7628, 7640, 7653, 7666, 7677, 7690, 7702, 7714, 7727, 7738, 7750, 7762, 7775, 7786, 7799, 7812, 7823, 7836, 7848, 7859, 7871, 7884, 7896, 7909, 7921, 7933, 7946, 7958, 7971, 7984, 7996, 8007, 8019, 8032, 8044, 8056, 8069, 8081, 8094, 8107, 8119, 8131, 8143, 8155, 8167, 8179, 8192, 8205, 8218, 8230, 8244, 8255, 8267, 8279, 8291, 8303, 8315, 8328, 8340, 8353, 8366, 8378, 8392, 8404, 8417, 8431, 8443, 8455, 8467, 8479, 8492, 8504, 8516, 8529, 8543, 8555, 8567, 8580, 8593, 8606, 8619, 8632, 8644, 8658, 8670, 8683, 8695, 8708, 8721, 8733, 8746, 8759, 8771, 8783, 8795, 8808, 8821, 8833, 8845, 8858, 8871, 8885, 8898, 8910, 8923, 8936, 8949, 8960, 8973, 8986, 9000, 9012, 9025, 9038, 9051, 9064, 9076, 9089, 9102, 9114, 9126, 9139, 9151, 9164, 9177, 9191, 9204, 9217, 9230, 9243, 9255, 9268, 9281, 9294, 9307, 9320, 9333, 9345, 9358, 9371, 9384, 9398, 9412, 9424, 9437, 9450, 9462, 9475, 9488, 9501, 9514, 9528, 9542, 9554, 9567, 9581, 9593, 9606, 9619, 9632, 9645, 9658, 9671, 9682, 9695, 9708, 9721, 9735, 9749, 9762, 9776, 9789, 9802, 9815, 9828, 9842, 9855, 9867, 9880, 9893, 9906, 9920, 9933, 9947, 9960, 9974, 9987, 10000, 10014, 10027, 10040, 10054, 10067, 10081, 10095, 10107, 10120, 10134, 10148, 10161, 10175, 10188, 10201, 10214, 10228, 10241, 10254, 10267, 10280, 10294, 10309, 10322, 10335, 10348, 10362, 10374, 10387, 10401, 10415, 10428, 10441, 10455, 10469, 10482, 10497, 10510, 10523, 10537, 10551, 10565, 10579, 10593, 10606, 10621, 10634, 10647, 10661, 10675, 10689, 10704, 10719, 10732, 10746, 10760, 10774, 10788, 10802, 10815, 10829, 10843, 10856, 10871, 10884, 10898, 10913, 10927, 10940, 10955, 10970, 10984, 10999, 11013, 11027, 11042, 11056, 11071, 11086, 11100, 11114, 11128, 11142, 11158, 11171, 11186, 11200, 11213, 11228, 11241, 11255, 11270, 11284, 11299, 11314, 11328, 11342, 11356, 11370, 11385, 11399, 11413, 11429, 11445, 11460, 11474, 11489, 11503, 11518, 11533, 11549, 11563, 11577, 11592, 11607, 11621, 11637, 11651, 11665, 11680, 11694, 11708, 11725, 11740, 11754, 11768, 11784, 11798, 11813, 11828, 11843, 11858, 11874, 11888, 11904, 11920, 11933, 11948, 11964, 11979, 11993, 12009, 12024, 12041, 12058, 12071, 12087, 12102, 12117, 12132, 12148, 12165, 12179, 12195, 12210, 12226, 12241, 12256, 12273, 12288, 12304, 12320, 12335, 12350, 12365, 12382, 12398, 12414, 12430, 12446, 12462, 12478, 12495, 12511, 12525, 12541, 12556, 12575, 12591, 12605, 12622, 12638, 12653, 12671, 12686, 12705, 12721, 12739, 12756, 12772, 12788, 12806, 12822, 12839, 12855, 12873, 12890, 12908, 12923, 12941, 12960, 12975, 12992, 13009, 13024, 13040, 13059, 13076, 13092, 13109, 13128, 13145, 13161, 13179, 13194, 13216, 13233, 13249, 13266, 13287, 13303, 13322, 13337, 13357, 13375, 13392, 13410, 13424, 13446, 13465, 13480, 13499, 13517, 13533, 13559, 13575, 13595, 13612, 13632, 13650, 13670, 13687, 13706, 13726, 13744, 13765, 13783, 13803, 13822, 13841, 13860, 13879, 13897, 13917, 13936, 13960, 13979, 13996, 14019, 14040, 14057, 14077, 14102, 14122, 14141, 14163, 14184, 14202, 14225, 14244, 14265, 14287, 14312, 14336, 14356, 14375, 14393, 14420, 14438, 14465, 14483, 14500, 14536, 14555, 14575, 14604, 14619, 14648, 14668, 14691, 14725, 14748, 14770, 14788, 14818, 14840, 14862, 14888, 14921, 14939, 14969, 14996, 15022, 15051, 15075, 15098, 15130, 15149, 15167, 15218, 15237, 15276, 15297, 15333, 15356, 15379, 15418, 15447, 15481, 15508, 15530, 15574, 15599, 15643, 15680, 15697, 15743, 15759, 15775, 15813, 15845, 15877, 15911, 15931, 15968, 16014, 16049, 16077, 16088, 16138, 16149, 16185, 16200, 16241, 16280, 16296};

	public ABITracerTest() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of URL method, of class ABITracer.
	 */
	@Test
	public void testURL() throws Exception {
		URL resource = this.getClass().getResource("/3730.ab1");
		assertNotNull(resource);
		ABITrace tracer = new ABITrace(resource);
		assertNotNull(tracer);
	}

	/**
	 * Test of Local file method, of class ABITracer.
	 */
	@Test
	public void testLocal() throws Exception {
		URL resource = this.getClass().getResource("/3730.ab1");
		assertNotNull(resource);
		File file = new File(resource.toURI());
		assertNotNull(file);
		ABITrace tracer = new ABITrace(file);
		assertNotNull(tracer);

		//Test length of tracer for file 3730.ab1
		final int EXPECTED_TRACE_LENGTH = 16302;
		assertEquals(EXPECTED_TRACE_LENGTH, tracer.getTraceLength());
		//Test length of sequence for file 3730.ab1
		assertEquals(1165, tracer.getSequenceLength());

		//Test sequence of tracer for file 3730.ab1
		assertTrue(sequence.equals(tracer.getSequence().getSequenceAsString()));
		//Test array that represents the quality of tracer for file 3730.ab1
		assertArrayEquals(qual, tracer.getQcalls());
		//Test array that represents the baseline of tracer for file 3730.ab1
		assertArrayEquals(base, tracer.getBasecalls());
		//Test image of tracer for file 3730.ab1
		BufferedImage image = tracer.getImage(100,100);
		assertNotNull(image);
		
		Assert.assertThrows(CompoundNotFoundException.class, ()->tracer.getTrace("D"));
		for (String base: Arrays.asList(new String []{"A","T","C","G"})){
		    assertEquals(EXPECTED_TRACE_LENGTH, tracer.getTrace(base).length);
		}
	}
}
