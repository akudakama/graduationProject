-- Categories
INSERT INTO category (name) VALUES
('Smartphones'),
('Laptops'),
('Headphones'),
('Tablets'),
('Smartwatches'),
('Accessories');

-- Products
INSERT INTO product (name, description, price, image_url, stock_quantity, category_id) VALUES
-- Smartphones
('iPhone 15 Pro', 'iPhone 15 Pro – high-end Apple smartphone with A17 Pro chip and titanium body.', 1199.99, 'https://tse4.mm.bing.net/th/id/OIP.-Gbf-IsmRKG2m3K1B-5wJwHaEK?rs=1&pid=ImgDetMain&o=7&rm=3', 92, 1),
('Samsung Galaxy S24', 'Samsung Galaxy S24 – flagship Android phone with AMOLED display and powerful camera.', 1099.99, 'https://tse1.mm.bing.net/th/id/OIP.XBy3JSWC2R6KIHpdGPe4QAHaFP?rs=1&pid=ImgDetMain&o=7&rm=3', 112, 1),
('Google Pixel 8 Pro', 'Google Pixel 8 Pro – premium phone with stock Android and amazing camera software.', 999.99, 'https://tse3.mm.bing.net/th/id/OIP.Je3EA12lUFqSqRscK9ltkwHaE7?rs=1&pid=ImgDetMain&o=7&rm=3', 102, 1),
('OnePlus 12', 'OnePlus 12 – powerful flagship killer with fast charging and high-refresh AMOLED.', 899.99, 'https://www.oneplus.com/content/dam/oasis/page/2024/global/product/waffle/share.jpg', 175, 1),
('Xiaomi 14 Pro', 'Xiaomi 14 Pro – Snapdragon 8 Gen 3, Leica camera system, and sleek design.', 799.99, 'https://tse3.mm.bing.net/th/id/OIP.KxVNN-Z5E_b-ZsUsZP4mdgHaEK?rs=1&pid=ImgDetMain&o=7&rm=3', 21, 1),

-- Laptops
('MacBook Pro 14 M3', 'MacBook Pro 14 M3 – Apple Silicon power with Liquid Retina XDR display.', 2399.00, 'https://tse2.mm.bing.net/th/id/OIP.XKP7SbUO8Dm5bTl1Xo3sOwHaET?rs=1&pid=ImgDetMain&o=7&rm=3', 140, 2),
('Dell XPS 13', 'Dell XPS 13 – ultra-portable Windows laptop with InfinityEdge display.', 1399.99, 'https://tse1.mm.bing.net/th/id/OIP.09vFzSNA14Zvh3eLrqa5aAHaD6?rs=1&pid=ImgDetMain&o=7&rm=3', 86, 2),
('Lenovo ThinkPad X1', 'ThinkPad X1 Carbon – business-class ultrabook with legendary keyboard.', 1599.49, 'https://th.bing.com/th/id/R.97556f7798388da653c55ae1f7290553?rik=ozhUu5E4WAK6Yw&pid=ImgRaw&r=0', 145, 2),
('ASUS ZenBook 14', 'ASUS ZenBook 14 – stylish, lightweight laptop with OLED display.', 1249.00, 'https://tse3.mm.bing.net/th/id/OIP.s8Wx9iJ4_513w_bYhqQMSwHaFE?rs=1&pid=ImgDetMain&o=7&rm=3', 138, 2),
('HP Spectre x360', 'HP Spectre x360 – convertible laptop with touch screen and pen support.', 1349.00, 'https://tse2.mm.bing.net/th/id/OIP.qYqW_ebF99a6eM2C_AoAjwHaFy?rs=1&pid=ImgDetMain&o=7&rm=3', 44, 2),

-- Headphones
('Sony WH-1000XM5', 'Sony WH-1000XM5 – industry-leading noise canceling wireless headphones.', 399.99, 'https://tse3.mm.bing.net/th/id/OIP.gaF-Z--s-5gj8a9S1kkbBgHaHa?o=7rm=3&rs=1&pid=ImgDetMain&o=7&rm=3', 111, 3),
('Bose QuietComfort 45', 'Bose QC 45 – comfortable over-ear headphones with quiet mode.', 329.99, 'https://www.soundguys.com/wp-content/uploads/2021/10/SG_Bose-QC-45_9-1536x864.jpg', 192, 3),
('AirPods Max', 'AirPods Max – Apple premium over-ear headphones with spatial audio.', 549.00, 'https://tse2.mm.bing.net/th/id/OIP.v-_-DjJzSmXhx8NDGVq8IgHaE8?rs=1&pid=ImgDetMain&o=7&rm=3', 193, 3),
('Sennheiser Momentum 4', 'Momentum 4 – wireless headphones with audiophile-grade sound.', 379.99, 'https://tse1.mm.bing.net/th/id/OIP.voBSABGUMVEZ6M9MBZ6BNgHaEK?rs=1&pid=ImgDetMain&o=7&rm=3', 119, 3),
('Beats Studio Pro', 'Beats Studio Pro – bass-rich wireless headphones by Beats.', 349.99, 'https://cdn.headphonecheck.com/wp-content/uploads/Beats-Studio-Pro-1-1-scaled-1-1920x1080.jpg', 105, 3),

-- Tablets
('iPad Pro 12.9', 'iPad Pro – Apple’s flagship tablet with M2 chip and Pencil support.', 1099.00, 'https://tse3.mm.bing.net/th/id/OIP.OKr9_XppXXI2s5_FZp8X8gHaHD?rs=1&pid=ImgDetMain&o=7&rm=3', 13, 4),
('Samsung Galaxy Tab S9', 'Tab S9 – Android tablet with AMOLED display and DeX mode.', 999.00, 'https://tse2.mm.bing.net/th/id/OIP.Kol3s_lmvLO9zH1kofN3YgHaEK?rs=1&pid=ImgDetMain&o=7&rm=3', 166, 4),
('Xiaomi Pad 6', 'Xiaomi Pad 6 – powerful mid-range Android tablet with Snapdragon chip.', 499.00, 'https://tse3.mm.bing.net/th/id/OIP.VCKNuK6HbOJnTNCQShs14wHaFh?rs=1&pid=ImgDetMain&o=7&rm=3', 163, 4),
('Lenovo Tab P12', 'Tab P12 – affordable multimedia tablet with great display.', 429.00, 'https://tse4.mm.bing.net/th/id/OIP.Vf-sJcnJsk-6abdLCqx8uwHaF9?rs=1&pid=ImgDetMain&o=7&rm=3', 56, 4),
('Huawei MatePad Pro', 'MatePad Pro – HarmonyOS tablet with productivity focus.', 699.00, 'https://tse3.mm.bing.net/th/id/OIP.nDchzQjSvYz_khieYgNZ8wHaCe?rs=1&pid=ImgDetMain&o=7&rm=3', 104, 4),

-- Smartwatches
('Apple Watch Series 9', 'Apple Watch 9 – sleek design with health sensors and Always-On display.', 499.00, 'https://tse3.mm.bing.net/th/id/OIP.DB6Ss7m8imms_U5JCWbxHwHaHa?rs=1&pid=ImgDetMain&o=7&rm=3', 188, 5),
('Samsung Galaxy Watch 6', 'Galaxy Watch 6 – stylish smartwatch with ECG and BIA sensor.', 349.00, 'https://tse1.mm.bing.net/th/id/OIP.u048T-WRPZ0JVxSeQZDzJgHaEK?rs=1&pid=ImgDetMain&o=7&rm=3', 66, 5),
('Garmin Fenix 7', 'Garmin Fenix 7 – rugged GPS smartwatch for outdoor adventures.', 699.00, 'https://res.garmin.com/en/products/010-02776-00/g/54599-D-2.jpg', 16, 5),
('Fitbit Sense 2', 'Fitbit Sense 2 – advanced health smartwatch with stress tracking, ECG, and sleep insights.', 299.00, 'https://tse3.mm.bing.net/th/id/OIP.qeikrer39aZ2n3jezZTxewHaE8?rs=1&pid=ImgDetMain&o=7&rm=3', 96, 5),
('Huawei Watch GT 4', 'Huawei Watch GT 4 – stylish smartwatch with extended battery life and fitness tracking.', 249.00, 'https://tse2.mm.bing.net/th/id/OIP.UBHBJisUi4ODyn0fB4mkHwHaEK?rs=1&pid=ImgDetMain&o=7&rm=3', 110, 5),

-- Accessories
('Anker 20W Charger', 'Anker 20W Charger – compact USB-C power adapter with fast charging support.', 19.99, 'https://tse3.mm.bing.net/th/id/OIP.sVLmwGcuqT_EXJWYf9lT7gHaEl?rs=1&pid=ImgDetMain&o=7&rm=3', 80, 6),
('USB-C to Lightning Cable', 'USB-C to Lightning Cable – Apple-certified durable cable for fast charging and data transfer.', 14.99, 'https://tse2.mm.bing.net/th/id/OIP.Zf6Cz4pUykEP0fep_uIWTQHaHa?rs=1&pid=ImgDetMain&o=7&rm=3', 176, 6),
('Laptop Stand', 'Laptop Stand – ergonomic aluminum stand for better posture, cooling, and desk organization.', 29.99, 'https://m.media-amazon.com/images/I/61sW7lJohEL._AC_.jpg', 195, 6),
('Bluetooth Speaker', 'Bluetooth Speaker – portable wireless speaker with deep bass and up to 12h battery life.', 49.99, 'https://i5.walmartimages.com/asr/79471941-7770-4ed6-8615-ea5f7b50f8be_1.d27064a1bbec8db9a8fa957d79eba245.jpeg', 137, 6),
('Webcam Logitech C920', 'Logitech C920 – Full HD 1080p webcam with stereo microphones, autofocus, and low-light correction.', 89.99, 'https://tse4.mm.bing.net/th/id/OIP.V3zoCE4yk4uToAv_KW6iUAHaHa?rs=1&pid=ImgDetMain&o=7&rm=3', 146, 6);
