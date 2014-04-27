package sample
import org.apache.commons.lang.builder.HashCodeBuilder
class ApacheCommon {
    static main(args) {
	def jj = new HashCodeBuilder(45, 23).append("SsF").toHashCode();

	println "${jj}"
    }
}
