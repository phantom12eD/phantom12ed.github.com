import datetime

letra_a_bits = {
    'A': "001010",
    'B': "001011",
    'C': "001100",
    'D': "001101",
    'E': "001110",
    'F': "001111",
    'G': "010000",
    'H': "010001",
    'I': "010010",
    'J': "010011",
    'K': "010100",
    'L': "010101",
    'M': "010110",
    'N': "010111",
    'O': "011000",
    'P': "011001",
    'Q': "011010",
    'R': "011011",
    'S': "011100",
    'T': "011101",
    'U': "011110",
    'V': "011111",
    'W': "100000",
    'X': "100001",
    'Y': "100010",
    'Z': "100011",
    '0': "000000",
    '1': "000001",
    '2': "000010",
    '3': "000011"
}

nombre = input("Introduce un nombre: ")
password = input("Introduce una contraseña: ")
texto = input("Introduce un texto: ")
binary_nombre = ""
binary_password = ""
binary_texto = ""

for char in nombre:
    binary_nombre += letra_a_bits.get(char.upper(), "")

for char in password:
    binary_password += letra_a_bits.get(char.upper(), "")

for char in texto:
    binary_texto += letra_a_bits.get(char.upper(), "")

print("Nombre en binario: ", binary_nombre)
print("Contraseña en binario: ", binary_password)
print("Texto en binario: ", binary_texto)

filename = datetime.datetime.now().strftime("%Y-%m-%d_%H-%M-%S") + ".txt"
with open(filename, "w") as file:
    file.write("Nombre: " + binary_nombre + "\n")
    file.write("Contraseña: " + binary_password + "\n")
    file.write("Texto: " + binary_texto)
