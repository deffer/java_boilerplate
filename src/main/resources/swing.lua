local borderLayout = luajava.bindClass("java.awt.BorderLayout")
local jframe = luajava.bindClass("javax.swing.JFrame")

local secretObject = luajava.newInstance("org.tests.luaj.ProtectedClass")
print(tostring(secretObject).." of class "..tostring(type(secretObject)))
local secretText = secretObject:getSecret()

local frame = luajava.newInstance("javax.swing.JFrame", "Sample Luaj Application");
local content = frame:getContentPane()
local label = luajava.newInstance("javax.swing.JLabel", "I know the secret: "..secretText)
content:add(label, borderLayout.CENTER)
frame:setDefaultCloseOperation(jframe.EXIT_ON_CLOSE)
frame:pack()


frame:setVisible(true)

print("Window opened")

