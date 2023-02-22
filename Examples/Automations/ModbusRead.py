####
# To Run the Script, execute: python modbus_holding.py -i "<ip address>" -r <register address>
#
###
from pyModbusTCP.client import ModbusClient
import time
import argparse

parser = argparse.ArgumentParser(description='Add IP and Register values')
parser.add_argument("-i", "--IP", help="Add IP")
parser.add_argument("-r", "--Regs", help="Add Register")

# SERVER_HOST = "localhost"  # You can also mention here the external IP for Modbus Recorder
SERVER_HOST = parser.parse_args()
SERVER_PORT = 502
REGS = parser.parse_args()

c = ModbusClient()
# uncomment this line to see debug message
# c.debug(True)
# define modbus server host, port
c.host(SERVER_HOST.IP)
c.port(SERVER_PORT)
while True:
    # open or reconnect TCP to server

    if not c.is_open():
        if not c.open():
            print("unable to connect to " + SERVER_HOST.IP + ":" + str(SERVER_PORT))
    # if open() is ok, read register (Modbus function 0x03)
    if c.is_open():
        # read 10 registers at address 0, store result in regs list
        regs = c.read_holding_registers(int(REGS.Regs), 10)
        # if success display registers
        if regs:
            print("reg ad #0 to 9: " + str(regs))
            # print(regs)
    # sleep 2s before next polling
    time.sleep(2)
