package com.incopostes.balanca.util;

import org.springframework.stereotype.Service;

import com.incopostes.balanca.model.Peso;

import jssc.SerialPort;
import jssc.SerialPortException;

@Service
public class BalancaUtil {
	private final SerialPort serialPort = new SerialPort("COM1");

	public Peso retornaPeso() {
		Peso peso = new Peso();

		try {
			System.out.println("Port opened: " + serialPort.openPort());
			System.out.println("Params setted: " + serialPort.setParams(9600, 8, 1, 0));
			System.out.println("successfully writen to port: " + serialPort.writeBytes(new byte[] { 0x04 }));

			byte[] buffer = serialPort.readBytes(11); // Read 11 bytes from serial port

			String text = new String(buffer);

			text = text.replaceAll("[^0123456789]", "");

			if (text.isEmpty()) {
				peso.setPeso(0);
			} else {
				peso.setPeso(Integer.parseInt(text));
			}

		} catch (SerialPortException ex) {
			System.err.println(ex);
		} finally {
			try {
				System.out.println("Port closed: " + serialPort.closePort());
			} catch (SerialPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return peso;
	}
}
