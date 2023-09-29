import React from 'react'
import { Link } from 'react-router-dom'

function Excluir(){
    return(
        <div>
            <h1>Page Excluir</h1>
            <Link to="/">
                <button>Voltar pro Início</button>
            </Link>
        </div>
    )
}

export default Excluir