CREATE DATABASE IF NOT EXISTS paymentsdb;
USE paymentsdb;
CREATE TABLE IF NOT EXISTS payment (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  concepto VARCHAR(255),
  cantidad DOUBLE,
  quien_paga VARCHAR(255),
  quien_recibe VARCHAR(255),
  monto DOUBLE,
  estatus VARCHAR(255)
);
