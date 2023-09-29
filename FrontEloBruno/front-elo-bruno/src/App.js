import React from 'react';
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import { useParams } from 'react-router-dom';
import Editar from './pages/editar'
import Incluir from './pages/incluir'
import Listar from './pages/listar'
import Excluir from './pages/excluir'
//import { Link } from 'react-router-dom'

export default function App() {

  

  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/listar">Listar</Link>
            </li>
            <li>
              <Link to="/incluir">Incluir</Link>
            </li>
            <li>
              <Link to= {{ pathname: `/editar/${Incluir._id}` }}>Editar</Link>
            </li>
            <li>
              {/* 👇️ link to dynamic path */}
              <Link to="/users/4200">byId</Link>
            </li>
          </ul>
        </nav>

        {/* 👇️ Wrap your Route components in a Routes component */}
        <Routes>
          <Route path="/listar" Component={Listar}/>
          <Route path="/incluir" Component={Incluir}/>
          <Route path="/editar/:id" Component={Editar}/>
          <Route path="/excluir" Component={Excluir}/>
          {/* 👇️ handle dynamic path */}
          <Route path="/users/:userId" element={<Users />} />
          <Route path="/" element={<Home />} />
          {/* 👇️ only match this when no other routes match */}
          <Route
            path="*"
            element={
              <div>
                <h2>404 Page not found etc</h2>
              </div>
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

function Home() {
  return <h2>Home</h2>;
}

function About() {
  return <h2>About</h2>;
}

function Users() {
  const params = useParams();

  return <h2>Users: {params.userId}</h2>;
}