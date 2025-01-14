package graph;

import Exception.Edge.EdgeTypeException;
import Exception.Edge.EdgeNullVertexException;
import Exception.Edge.EdgeWeightException;
import Exception.Vertex.VertexLabelException;
import Exception.Vertex.VertexTypeException;
import edge.Edge;
import edge.NetworkConnection;
import vertex.*;

import java.io.IOException;

public class NetworkTopology extends ConcreteGraph {
    public NetworkTopology(String label) {
        super(label);
    }

    @Override
    public boolean addVertex(Vertex vertex) throws VertexTypeException, VertexLabelException {
        if (!((vertex instanceof Computer) || (vertex instanceof Router) || (vertex instanceof Server) || (vertex instanceof WirelessRouter)))
            throw new VertexTypeException(vertex.getLabel());
        return super.addVertex(vertex);
    }

    @Override
    public boolean addEdge(Edge edge) throws EdgeNullVertexException, EdgeTypeException, EdgeWeightException {
        if (!(edge instanceof NetworkConnection))
            throw new EdgeTypeException(getLabel());
        // 避免单重边中存在多充边，如果存在，就不添加这条边
        return !super.edges.containsKey(edge.getLabel()) && super.addEdge(edge);
    }
}
