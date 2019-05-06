# workflowRepository

Le projet contient des webservices pour afficher des données de la base de donnée
<ul>
  <li>L'URL : [contextPath]/workflowCategories : affiche tous les categories de workflow</li>
  <li>L'URL : [contextPath]/workflowCategory/{id} : affiche la categorie du workflow ayant cet id </li>
<li>L'URL : [contextPath]/workflows : affiche les workflows avec un filtre sous format json envoyé dans le body de la requête 
  Le format json : { 'catgeoryIds' : 'liste des ids separés par , ',
                      'name' : 'nom du workflow',
                       'enabled' : 'la status du workflow'
                   }
  </li>
</ul>

  Un fichier sql qui contient les requêtes nécessaires pour créer les tables de la base de données se trouve dans le chemin :  workflowApplication\sql\queries.sql
