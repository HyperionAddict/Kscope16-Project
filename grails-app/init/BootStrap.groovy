import com.cubehead.kscope16.EbServer
import com.essbase.api.session.IEssbase

class BootStrap {

    def init = { servletContext ->

        def svr = new EbServer()
        svr.name = 'sandboxcv'

        def essHome = IEssbase.Home.create(IEssbase.JAPI_VERSION)
        println 'JAPI found.'

        def tst = new File('grails-app/init/test.txt').canonicalPath
        println tst
        def pw = new File('grails-app/init/pw.txt').text.trim()
        println ":::${pw}:::"

        def essSvr = essHome.signOn('jaultman', pw, false, null, 'embedded', svr.name)
        println 'Embedded mode enabled.'

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
