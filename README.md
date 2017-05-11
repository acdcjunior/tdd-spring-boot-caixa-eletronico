# TDD + Caixa Eletrônico

    
# Gradle e IntelliJ
- O plugin 'idea' nao presta, pois soh cria file-based, nao directory-based
- Quando voce dah open->import as gradle, ele nao cata direito as build dirs, e um make do intellij (<kbd>ctrl+f9</kbd>)
cria um diretorio `out` bizonho na raiz
    - Quando eu dei open sem nada e ele, quando abriu, ofereceu pra importar como gradle, funcionou
     tudo (os filhos gerando tudo na pasta build, independentemente de eu ter usado o intellij ou o gradle itself)
    - Preciso confirmar pra saber se eh repetitivel