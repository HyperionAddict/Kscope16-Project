import com.cubehead.kscope16.EbServer
import com.essbase.api.session.IEssbase

class BootStrap {

    def init = { servletContext ->

        def svr = new EbServer()
        svr.name = 'sandboxcv'

        def essHome = IEssbase.Home.create(IEssbase.JAPI_VERSION)

        def pw = new File('grails-app/init/pw.txt').text.trim()

        def essSvr = essHome.signOn('jaultman', pw, false, null, 'embedded', svr.name)

        svr.apps = ''
        essSvr.applications.all.each {
            println it.name
            svr.apps += "$it.name "
        }

        essSvr.disconnect()
        essHome.signOff()

        svr.save()
    }

    def destroy = {
    }
}
