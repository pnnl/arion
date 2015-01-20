/**
 * 
 */
package gov.pnnl.prosser.api;

/**
 * @author happ546
 *
 */
public abstract class AbstractNs3Object {
	
	private String name;

	public AbstractNs3Object() {
		
	}

    public <T extends AbstractNs3Object, Z extends AbstractBuilder<T, Z>> AbstractNs3Object(final AbstractBuilder<T, Z> builder) {
    	
    }

	public void writeNs3String(final StringBuilder sb) {
		this.writeNs3Properties(sb);
	}

	public abstract void writeNs3Properties(StringBuilder sb);
	
	//public abstract void assignment(AbstractNs3Object right);

	public String getName() {
		return this.name;
	}
	
    public static abstract class AbstractBuilder<T extends AbstractNs3Object, Z extends AbstractBuilder<T, Z>> {
        protected String name;

        protected String groupId;

        protected abstract Z self();

        public abstract T build();

        public Z name(final String name) {
            this.name = name;
            return self();
        }

        public Z groupId(final String groupId) {
            this.groupId = groupId;
            return self();
        }
    }
	
}
