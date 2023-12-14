import tkinter as tk
import requests

# Remplacez les valeurs suivantes par vos informations spécifiques
GITHUB_USERNAME = 'houtreille'
GITHUB_REPO = 'quantitativePricing'
ACCESS_TOKEN = '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'

api_url = f"https://api.github.com/repos/{GITHUB_USERNAME}/{GITHUB_REPO}/actions/runs"

headers = {
    'Authorization': f'token {ACCESS_TOKEN}',
    'Accept': 'application/vnd.github.v3+json'
}

response = requests.get(api_url, headers=headers)
data = response.json()

# display content of data
print(data)

# Récupération des informations sur les workflows
workflows = data['workflow_runs']

# Création de la fenêtre Tkinter
root = tk.Tk()
root.title("État des workflows GitHub Actions")

# Affichage de l'état des workflows dans une fenêtre Tkinter
for workflow in workflows:
    workflow_name = workflow['name']
    status = workflow['status']
    conclusion = workflow['conclusion'] if 'conclusion' in workflow else None

    status_text = f"{workflow_name}: {status}"
    if conclusion:
        status_text += f" - Conclusion: {conclusion}"

    label = tk.Label(root, text=status_text)
    label.pack()

root.mainloop()
