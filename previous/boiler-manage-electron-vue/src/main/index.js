import { app, BrowserWindow,ipcMain} from 'electron'
import {autoUpdater} from 'electron-updater'

if (process.env.NODE_ENV !== 'development') {
    global.__static = require('path').join(__dirname, '/static').replace(/\\/g, '\\\\')
}
let mainWindow
const winURL = process.env.NODE_ENV === 'development'
    ? `http://localhost:9081`
    : `file://${__dirname}/index.html`

function createWindow () {
    mainWindow = new BrowserWindow({
        title:"锅炉远程监控平台",
        icon: __static+'/common/icon.ico',
        width: 480,
        height: 470,
        frame:false,
        maximizable:false,
        webPreferences: {webSecurity: false},
    })
    mainWindow.loadURL(winURL)
    //mainWindow.webContents.openDevTools();
    mainWindow.on('closed', () => {
        mainWindow = null
    })
}
ipcMain.on('closeMainWindow', (event, arg) => {
    mainWindow.close()
    mainWindow=null
})
app.on('ready', createWindow)
app.on('activate', () => {
    if (mainWindow === null) {
        createWindow()
    }
})
app.on('window-all-closed', () => {
    if (process.platform !== 'darwin') {
        app.quit()
    }
})
/*****************************************************升级程序********************************************************/
const uploadUrl = "http://autoupdate.sdcsoft.com.cn/files/BoilerPlantSystem/"; // 下载地址，不加后面的**.exe
autoUpdater.setFeedURL(uploadUrl);
autoUpdater.on('update-downloaded', () => {
    autoUpdater.quitAndInstall()
})
app.on('ready', () => {
    if (process.env.NODE_ENV === 'production') autoUpdater.checkForUpdates()
})